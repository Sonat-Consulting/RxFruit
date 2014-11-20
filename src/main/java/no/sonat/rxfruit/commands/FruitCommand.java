package no.sonat.rxfruit.commands;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import no.sonat.rxfruit.Client;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lars on 19.11.14.
 */
public class FruitCommand extends HystrixObservableCommand<String> {

    public final String fruitName;
    public final int numberOfFruits;

    public FruitCommand(String fruitName, int numberOfFruits) {
        super(HystrixCommandGroupKey.Factory.asKey("TheFruityGroup"));
        this.fruitName = fruitName;
        this.numberOfFruits = numberOfFruits;
    }


    @Override
    protected Observable<String> run() {

        new FruitGetterCommand(fruitName, numberOfFruits).observe().map(new Func1<String[], String>() {

            @Override
            public String call(String[] strings) {
                return null;
            }
        });

        String[] fruits = new Client().requestFruit(fruitName, numberOfFruits);

        return Observable.from(fruits);
    }
}
