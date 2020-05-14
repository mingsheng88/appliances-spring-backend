package com.example.appliances.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApplianceTest {

    // TODO: Pending verifications
    // => serialNo cannot be null
    // => brand cannot be null
    // => model cannot be null
    // => status cannot be null

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void GivenInvalidJson_WhenDeserializing_ThenThrowError() throws IOException {
        String jsonString = "{\"id\":\"0881fd35-c247-46ba-ae4e-bb62278f2f81\",\"serialNo\":\"serial number\",\"brand\":\"brandy\",\"model\":\"modelling\",\"status\":\"hotcake\",\"purchaseDate\":\"2020-01-03\"}";

        Appliance appliance = objectMapper.readValue(jsonString, Appliance.class);

        assertThat(appliance.getSerialNo(), containsString("SERIAL NUMBER"));
        assertThat(appliance.getBrand(), containsString("BRANDY"));
        assertThat(appliance.getModel(), containsString("MODELLING"));
        assertThat(appliance.getStatus(), containsString("hotcake"));
        assertEquals(appliance.getPurchaseDate(), Date.valueOf("2020-01-03"));
    }

    @Test
    public void GivenInvalidDateString_WhenDeserializing_ThenThrowError() throws IOException {
        String jsonString = "{\"id\":\"0881fd35-c247-46ba-ae4e-bb62278f2f81\",\"serialNo\":\"serial number\",\"brand\":\"brandy\",\"model\":\"modelling\",\"status\":\"hotcake\",\"purchaseDate\":\"NOT A DATE STRING\"}";

        Exception exception = assertThrows(ValueInstantiationException.class, () -> {
            objectMapper.readValue(jsonString, Appliance.class);
        });
    }
}


