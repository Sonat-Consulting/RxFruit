package no.sonat.rxfruit.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lars on 19.11.14.
 */
public class FruitCommandTest {

    @Test
    public void testFruitCommand() {
        FruitGetterCommand getTwoApplesCommand = new FruitGetterCommand("apple", 2);

        int counter = 0;
        for (String[] apples : getTwoApplesCommand.observe().toBlocking().toIterable()) {
            for (String apple : apples) {
                counter++;
                assertEquals("apple", apple);
            }
        }

        assertEquals(2, counter);
    }
}
