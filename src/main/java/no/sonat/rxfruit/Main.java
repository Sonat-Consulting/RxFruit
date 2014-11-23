package no.sonat.rxfruit;

import no.sonat.rxfruit.commands.FruitCommand;
import no.sonat.rxfruit.commands.RetryFruitCommand;
import rx.Observable;

import java.util.List;

/**
 * Created by lars on 18.11.14.
 */
public class Main {

    public static void main(String[] args) {

        Observable<List<Fruit>> getTwoApplesCommandObservable = new FruitCommand("apple", 2).observe();
        Observable<List<Fruit>> getOneBananaCommandObservable = new FruitCommand("banana", 1).observe();
        Observable<List<Fruit>> getOnePearCommandObservable = new FruitCommand("pear", 3).observe();
        Observable<List<Fruit>> getABowlCommandObservable = new RetryFruitCommand("bowl", 1).observe();
        Observable<List<Fruit>> getASpoonCommandObservable = new RetryFruitCommand("spoon", 1).observe();

        Observable<List<Fruit>> mergedObservable = Observable.merge(
                getTwoApplesCommandObservable,
                getOneBananaCommandObservable,
                getOnePearCommandObservable,
                getABowlCommandObservable,
                getASpoonCommandObservable
        );

        mergedObservable.subscribe(Main::onNext, Main::onError, Main::onComplete);
    }

    private static void onNext(List<Fruit> fruitNames) {
        if (fruitNames.get(0) instanceof Error) {
            Error err = (Error) fruitNames.get(0);
            System.out.println( String.format( "[%s - %s]", err.getException().getClass().getName(), err.getException().getMessage() ));
            return;
        }
        boolean plural = fruitNames.size() > 1;
        System.out.println(String.format( "%s %s%s", fruitNames.size(), fruitNames.get(0), plural ? "s" : ""));
    }

    private static void onError(Throwable t) {
        t.printStackTrace();
    }

    private static void onComplete() {
        System.out.println("Fruit salad complete. Now exiting.");
        System.exit(0);
    }
}
