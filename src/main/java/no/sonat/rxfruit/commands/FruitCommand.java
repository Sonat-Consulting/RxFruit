package no.sonat.rxfruit.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandProperties;
import no.sonat.rxfruit.Client;
import no.sonat.rxfruit.domain.*;

import java.util.Arrays;
import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory;

/**
 * Created by lars on 20.11.14.
 */
public class FruitCommand extends HystrixCommand<FruitSaladEssential> {

    public final String fruitName;
    public final int numberOfFruits;

    public FruitCommand(String fruitName, int numberOfFruits) {
        super(Setter.withGroupKey(Factory.asKey("TheFruityGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(5000)));
        this.fruitName = fruitName;
        this.numberOfFruits = numberOfFruits;
    }

    @Override
    protected FruitSaladEssential run() throws Exception {

        List<String> fruitList = Arrays.asList( new Client()
                .withErrorPercent(25)
                .requestFruit(fruitName, numberOfFruits) );
        String type = fruitList.get(0);

        switch(type) {
            case "bowl":
            case "spoon":
            case "fat":
            case "skje":
                return new EssentialItem(type, "a " + type);
            case "cream":
            case "krem":
            case "vanilla":
                return new Cream(type, type, fruitList.size());
            default:
                return new Fruit(type, type, fruitList.size());
        }


    }

    @Override
    protected FruitSaladEssential getFallback() {
        return new FruitSaladError(getFailedExecutionException());
    }


}
