package com.example.csc340hw2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Csc340hw2Application {

    public static void main(String[] args) {
        SpringApplication.run(Csc340hw2Application.class, args);
        ravenPrice();
        System.exit(0);
    }

    public static void ravenPrice() {
        try {
            String url = "https://api.coinlore.net/api/ticker/?id=32386";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonPrice = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPrice);

            //gets coin name
            String coinName = root.findValue("name").asText();
            //gets coin value in USD
            double coinPrice = root.findValue("price_usd").asDouble();
            //print vals
            System.out.println("Coin: " + coinName);
            System.out.println("Price: " + coinPrice);

        } catch (JsonProcessingException ex) {
            System.out.println("error in ravenPrice");
        }
    }
}
