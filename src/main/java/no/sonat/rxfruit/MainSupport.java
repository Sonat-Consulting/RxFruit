package no.sonat.rxfruit;

import com.netflix.hystrix.HystrixExecutable;
import no.sonat.rxfruit.commands.FruitCommand;
import no.sonat.rxfruit.commands.RetryFruitCommand;
import no.sonat.rxfruit.domain.FruitSaladEssential;

/**
 * Created by lars on 26.11.14.
 */
public abstract class MainSupport {

    @SuppressWarnings("unchecked")
    protected void run() {
        processOrder(new HystrixExecutable[]{
                new FruitCommand("apple", 2),
                new FruitCommand("banana", 1),
                new FruitCommand("pear", 3),
                new RetryFruitCommand("bowl", 1),
                new RetryFruitCommand("spoon", 1)
        });
    }

    protected abstract void processOrder(HystrixExecutable<FruitSaladEssential>[] fruityCommands);

}
