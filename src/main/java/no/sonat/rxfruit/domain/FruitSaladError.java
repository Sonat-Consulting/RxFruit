package no.sonat.rxfruit.domain;

import no.sonat.rxfruit.*;

/**
 * Created by lars on 26.11.14.
 */
public class FruitSaladError implements FruitSaladEssential, no.sonat.rxfruit.Error {

    private final Throwable exception;

    public FruitSaladError(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public String type() {
        return "error";
    }

    @Override
    public String description() {
        return "A fruity error";
    }

    @Override
    public String toString() {
        return "A fruity error - " + getException().getMessage();
    }
}
