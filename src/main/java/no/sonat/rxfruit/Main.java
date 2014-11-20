package no.sonat.rxfruit;

import no.sonat.rxfruit.commands.FruitCommand;
import rx.Observable;

/**
 * Created by lars on 18.11.14.
 */
public class Main {

    public static void main(String[] args) {

        Observable<String[]> getTwoApplesCommandObservable = new FruitCommand("apple", 2).observe();
        Observable<String[]> getOneBananaCommandObservable = new FruitCommand("banana", 1).observe();
        Observable<String[]> getOnePearCommandObservable = new FruitCommand("pear", 3).observe();
        Observable<String[]> getABowlCommandObservable = new FruitCommand("bowl", 1).observe();
        Observable<String[]> getASpoonCommandObservable = new FruitCommand("spoon", 1).observe();

        Observable<String[]> mergedObservable = Observable.merge(
                getTwoApplesCommandObservable,
                getOneBananaCommandObservable,
                getOnePearCommandObservable,
                getABowlCommandObservable,
                getASpoonCommandObservable
        );

        mergedObservable.subscribe(Main::onNext, Main::onError, Main::onComplete);
    }

    private static void onNext(String[] fruitNames) {
        boolean plural = fruitNames.length > 1;
        System.out.println(String.format( "%s %s%s", fruitNames.length, fruitNames[0], plural ? "s" : ""));
    }

    private static void onError(Throwable t) {
        t.printStackTrace();
    }

    private static void onComplete() {
        System.out.println("Fruit salad complete. Now exiting.");
        System.exit(0);
    }
}
