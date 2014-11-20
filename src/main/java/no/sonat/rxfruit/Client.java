package no.sonat.rxfruit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by lars on 19.11.14.
 */
public class Client {

    public String[] requestFruit(String itemName, int numberOfItems) {

        try {
            URI uri = null;

//            uri = new URIBuilder()
//                    .setScheme("http")
//                    .setHost("fruityfruit.azurewebsites.net")
//                    .setPath(String.format("/fruit/%s/%s", itemName, numberOfItems))
//                    .build();

            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("ec2-54-187-76-112.us-west-2.compute.amazonaws.com")
                    .setPort(9000)
                    .setPath(String.format("/fruit/%s/%s", itemName, numberOfItems))
                    .setParameter("errorRateInPercent", "0").build();


            String stringRes = Request.Get(uri)
                    .connectTimeout(5000)
                    .socketTimeout(5000)
                    .execute().returnContent().asString();

            String[] fruit = new ObjectMapper().readValue(stringRes, String[].class);

            return fruit;

        } catch(Exception e) {
            throw new RuntimeException("Dammit! Something went wrong", e);
        }
    }
}
