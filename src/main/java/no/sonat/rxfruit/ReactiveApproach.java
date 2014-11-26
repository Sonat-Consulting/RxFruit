package no.sonat.rxfruit;

import no.sonat.rxfruit.commands.FruitCommand;
import no.sonat.rxfruit.commands.RetryFruitCommand;
import no.sonat.rxfruit.domain.CutableIngredient;
import no.sonat.rxfruit.domain.FruitSaladEssential;
import no.sonat.rxfruit.domain.Ingredient;
import no.sonat.rxfruit.domain.WipableIngredient;
import rx.Observable;

/**
 * Created by lars on 18.11.14.
 */
public class ReactiveApproach {

    public static void main(String[] args) {

        Observable<FruitSaladEssential> getTwoApplesCommandObservable = new FruitCommand("apple", 2).observe();
        Observable<FruitSaladEssential> getOneBananaCommandObservable = new FruitCommand("banana", 1).observe();
        Observable<FruitSaladEssential> getOnePearCommandObservable = new FruitCommand("pear", 3).observe();
        Observable<FruitSaladEssential> getABowlCommandObservable = new RetryFruitCommand("bowl", 1).observe();
        Observable<FruitSaladEssential> getASpoonCommandObservable = new RetryFruitCommand("spoon", 1).observe();

        Observable<FruitSaladEssential> mergedObservable = Observable.merge(
                getTwoApplesCommandObservable,
                getOneBananaCommandObservable,
                getOnePearCommandObservable,
                getABowlCommandObservable,
                getASpoonCommandObservable
        );

        mergedObservable.subscribe(ReactiveApproach::onNext, ReactiveApproach::onError, ReactiveApproach::onComplete);
    }

    private static void onNext(FruitSaladEssential item) {
        if (item instanceof Error) {
            Error err = (Error) item;
            System.out.println( String.format( "[%s - %s]", err.getException().getClass().getName(), err.getException().getMessage() ));
            return;
        }

        System.out.println("Got " + item.toString());

        if (item instanceof CutableIngredient) {
            ((CutableIngredient) item).cut();
        } else if (item instanceof WipableIngredient) {
            ((WipableIngredient) item).wip();
        }

        if (item instanceof Ingredient) {
            Ingredient ingr = (Ingredient) item;
            boolean plural = ingr.quantity() > 1;
            System.out.println(String.format("Adding ingredient: %s %s%s", ingr.quantity(), ingr.type(), plural ? "s" : ""));
        } else {
            System.out.println(String.format("Readying %s", item.description()));
        }
    }

    private static void onError(Throwable t) {
        t.printStackTrace();
    }

    private static void onComplete() {
        System.out.println("Fruit salad complete. Now exiting.");
        System.exit(0);
    }
}
