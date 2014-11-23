package no.sonat.rxfruit.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import no.sonat.rxfruit.*;
import no.sonat.rxfruit.Error;

import java.util.List;

/**
 * Created by lars on 23.11.14.
 */
public class RetryFruitCommand extends HystrixCommand<List<Fruit>> {

    public final String fruitName;
    public final int numberOfFruits;

    public RetryFruitCommand(String fruitName, int numberOfFruits) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TheFruityGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(10000)));

        this.fruitName = fruitName;
        this.numberOfFruits = numberOfFruits;
    }

    @Override
    protected List<Fruit> run() throws Exception {

        Throwable lastException = null;
        int retryNumber;

        for (retryNumber = 0; retryNumber < 3; retryNumber++) {

            // If this is a retry, wait for 2 seconds before trying again.
            if (retryNumber > 0) {
                Thread.sleep(2000);
            }

            List<Fruit> fruitList = new FruitCommand(fruitName, numberOfFruits).execute();

            if (fruitList.get(0) instanceof no.sonat.rxfruit.Error) {
                // keep error message and retry
                lastException = ((Error) fruitList.get(0)).getException();
                System.out.println(String.format("RetryFruitCommand for fruitName=%s failes. Retrying in 2 seconds", fruitName));
            } else {
                // everything is good, return result
                return fruitList;
            }
        }

        throw new RetryFruitException(String.format("FruitRetryCommand is still failing after %s retries.", retryNumber), lastException);
    }
}
