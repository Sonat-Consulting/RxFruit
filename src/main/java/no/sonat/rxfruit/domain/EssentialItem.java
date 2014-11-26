package no.sonat.rxfruit.domain;

/**
 * Created by lars on 26.11.14.
 */
public class EssentialItem implements FruitSaladEssential {

    private final String type;
    private final String description;

    public EssentialItem(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String type() {
        return this.type;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String toString() {
        return description;
    }
}
