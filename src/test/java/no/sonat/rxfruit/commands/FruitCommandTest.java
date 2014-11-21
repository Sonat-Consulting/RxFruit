package no.sonat.rxfruit.commands;

import no.sonat.rxfruit.Fruit;
import org.junit.Test;

import java.util.List;

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
        for (List<Fruit> apples : getTwoApplesCommand.observe().toBlocking().toIterable()) {
            for (Fruit apple : apples) {
                counter++;
                assertEquals("apple", apple.name);
            }
        }

        assertEquals(2, counter);
    }
}
