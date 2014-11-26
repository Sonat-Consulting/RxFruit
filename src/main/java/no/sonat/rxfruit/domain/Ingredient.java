package no.sonat.rxfruit.domain;

/**
 * Created by lars on 26.11.14.
 */
public interface Ingredient extends FruitSaladEssential {

    int quantity();
    String unit();
}
