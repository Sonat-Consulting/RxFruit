package no.sonat.rxfruit.domain;

/**
 * Created by lars on 26.11.14.
 */
public class Cream extends BaseIngredient implements WipableIngredient {
    public Cream(String type, String description, int quantity) {
        super(type, description, quantity, "ml");
    }

    @Override
    public void wip() {
        try {
            System.out.println("Wipping " + description());
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
