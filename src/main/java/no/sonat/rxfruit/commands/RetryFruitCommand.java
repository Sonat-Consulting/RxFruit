package no.sonat.rxfruit.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import no.sonat.rxfruit.*;
import no.sonat.rxfruit.Error;
import no.sonat.rxfruit.domain.FruitSaladEssential;

import java.util.List;

/**
 * Created by lars on 23.11.14.
 */
public class RetryFruitCommand extends HystrixCommand<FruitSaladEssential> {

    public final String fruitName;
    public final int numberOfFruits;

    public RetryFruitCommand(String fruitName, int numberOfFruits) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TheFruityGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(10000)));

        this.fruitName = fruitName;
        this.numberOfFruits = numberOfFruits;
    }

    @Override
    protected FruitSaladEssential run() throws Exception {

        Throwable lastException = null;
        int retryNumber;

        for (retryNumber = 0; retryNumber < 3; retryNumber++) {

            // If this is a retry, wait for 2 seconds before trying again.
            if (retryNumber > 0) {
                Thread.sleep(2000);
            }

            FruitSaladEssential fruitSaladEssential = new FruitCommand(fruitName, numberOfFruits).execute();

            if (fruitSaladEssential instanceof Error) {
                // keep error message and retry
                lastException = ((Error) fruitSaladEssential).getException();
                System.out.println(String.format("RetryFruitCommand for fruitName=%s failes. Retrying in 2 seconds", fruitName));
            } else {
                // everything is honky-donkey, return result.
                return fruitSaladEssential;
            }
        }

        throw new RetryFruitException(String.format("FruitRetryCommand is still failing after %s retries.", retryNumber), lastException);
    }
}
