package no.sonat.rxfruit;

/**
 * Created by lars on 21.11.14.
 */
public class FruitError extends Fruit implements Error {

    private final Throwable exception;

    public FruitError(Throwable exception) {
        super("");
        this.exception = exception;
    }

    @Override
    public Throwable getException() {
        return exception;
    }
}
