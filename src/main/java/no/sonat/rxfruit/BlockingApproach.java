package no.sonat.rxfruit;

import com.netflix.hystrix.HystrixExecutable;
import no.sonat.rxfruit.domain.FruitSaladEssential;

/**
 * Created by lars on 26.11.14.
 */
public class BlockingApproach extends MainSupport {

    public static void main(String[] args) {
        new BlockingApproach().run();
    }


    @Override
    protected void processOrder(HystrixExecutable<FruitSaladEssential>[] fruityCommands) {
        
    }
}
