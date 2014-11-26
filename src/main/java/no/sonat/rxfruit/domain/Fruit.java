package no.sonat.rxfruit.domain;

/**
 * Created by lars on 26.11.14.
 */
public class Fruit extends BaseIngredient implements CutableIngredient {


    public Fruit(String type, String description, int quantity) {
        super(type, description, quantity, "stk");
    }

    @Override
    public void cut() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
