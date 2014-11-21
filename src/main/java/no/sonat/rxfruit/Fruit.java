package no.sonat.rxfruit;

/**
 * Created by lars on 21.11.14.
 */
public class Fruit {

    public final String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
