package no.sonat.rxfruit.commands;

import no.sonat.rxfruit.domain.Fruit;
import no.sonat.rxfruit.domain.FruitSaladEssential;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lars on 19.11.14.
 */
public class FruitCommandTest {

    @Test
    public void testFruitCommand() {
        FruitCommand getTwoApplesCommand = new FruitCommand("apple", 2);

        int counter = 0;
        FruitSaladEssential apples = getTwoApplesCommand.execute();

        assertTrue(apples instanceof Fruit);


        assertEquals(2, ((Fruit)apples).quantity());
    }
}
