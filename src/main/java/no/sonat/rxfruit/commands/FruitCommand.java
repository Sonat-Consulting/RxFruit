package no.sonat.rxfruit.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandProperties;
import no.sonat.rxfruit.Client;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory;

/**
 * Created by lars on 20.11.14.
 */
public class FruitCommand extends HystrixCommand<String[]> {

    public final String fruitName;
    public final int numberOfFruits;

    public FruitCommand(String fruitName, int numberOfFruits) {
        super(Setter.withGroupKey(Factory.asKey("TheFruityGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(5000)));
        this.fruitName = fruitName;
        this.numberOfFruits = numberOfFruits;
    }

    @Override
    protected String[] run() throws Exception {
        return new Client().requestFruit(fruitName, numberOfFruits);
    }
}