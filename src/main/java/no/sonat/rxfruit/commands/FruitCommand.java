package no.sonat.rxfruit.commands;

import com.google.common.collect.Lists;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandProperties;
import no.sonat.rxfruit.Client;
import no.sonat.rxfruit.Fruit;
import no.sonat.rxfruit.FruitError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory;

/**
 * Created by lars on 20.11.14.
 */
public class FruitCommand extends HystrixCommand<List<Fruit>> {

    public final String fruitName;
    public final int numberOfFruits;

    public FruitCommand(String fruitName, int numberOfFruits) {
        super(Setter.withGroupKey(Factory.asKey("TheFruityGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(5000)));
        this.fruitName = fruitName;
        this.numberOfFruits = numberOfFruits;
    }

    @Override
    protected List<Fruit> run() throws Exception {

        List<String> fruitList = Arrays.asList( new Client()
                .withErrorPercent(25)
                .requestFruit(fruitName, numberOfFruits) );

        return Lists.transform(fruitList, Fruit::new);
    }

    @Override
    protected List<Fruit> getFallback() {
        return new ArrayList<Fruit>(){{
            add(new FruitError(getFailedExecutionException()));
        }};
    }


}
