package no.sonat.rxfruit;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by lars on 19.11.14.
 */
public class ClientTest {

    @Test
    public void testClient() throws IOException, URISyntaxException {
        String[] twoApples = new Client().requestFruit("apple", 2);

        assertThat(twoApples.length, is(equalTo(2)));
        assertThat(twoApples[0], is(equalTo("apple")));
        assertThat(twoApples[1], is(equalTo("apple")));
    }
}
