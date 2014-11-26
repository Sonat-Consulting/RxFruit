package no.sonat.rxfruit.domain;

/**
 * Created by lars on 26.11.14.
 */
public class BaseIngredient extends EssentialItem implements Ingredient {

    private final int quantity;
    private final String unit;

    public BaseIngredient(String type, String description, int quantity, String unit) {
        super(type, description);
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public int quantity() {
        return 0;
    }

    @Override
    public String unit() {
        return null;
    }
}
