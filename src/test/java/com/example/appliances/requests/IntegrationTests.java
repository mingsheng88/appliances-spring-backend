package com.example.appliances.requests;


import com.example.appliances.AppliancesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
public class IntegrationTests {

    @LocalServerPort
    private int port;

    // GET api/v1/appliances
    // => when empty, return []
    // => when 1 object, return object in the correct format
    // => when 3 objects, return 3 objects in the correct format
    // => when 3 objects + search by keyword, return 2 matched objects
    // => when 3 objects + search by keyword, return 1 matched object

    // POST api/v1/appliances
    // => when erroneous input is received, return correct error message
    // => when validation fails, return correct error message
    // => when correct input is received, return 200 with object created

    // PATCH api/v1/appliances/{id}
    // => when no input is received, return correct error message
    // => when invalid id, return correct error message
    // => when validation fails, return correct error message
    // => when brand is submitted, return 200 with correctly updated object

    // GET api/v1/appliances/{id}
    // => when invalid id, return correct error message
    // => when found, return object in the correct format
}
