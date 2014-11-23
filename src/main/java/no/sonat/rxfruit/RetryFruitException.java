package no.sonat.rxfruit;

/**
 * Created by lars on 23.11.14.
 */
public class RetryFruitException extends Exception {

    public RetryFruitException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
