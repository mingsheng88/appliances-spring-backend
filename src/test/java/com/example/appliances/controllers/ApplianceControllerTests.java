package com.example.appliances.controllers;

import com.example.appliances.entities.Appliance;
import com.example.appliances.repositories.ApplianceRepository;
import com.example.appliances.services.ApplianceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApplianceController.class)
public class ApplianceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ApplianceRepository applianceRepository;

    @MockBean
    private ApplianceService applianceService;

    @Test
    public void GivenNoParams_WhenGetAppliances_ThenReturnResultsFromService() throws Exception {
        Appliance a1 = new Appliance(UUID.randomUUID(), "serial", "brand", "model", "status", "2020-01-01");
        Appliance a2 = new Appliance(UUID.randomUUID(), "serial2", "brand2", "model2", "status2", "2020-02-02");
        List<Appliance> list = new ArrayList<Appliance>();
        list.add(a1);
        list.add(a2);

        when(applianceService.getAppliances(null)).thenReturn(list);

        this.mockMvc.perform(get("/api/v1/appliances"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", containsString(a1.getId().toString())))
                .andExpect(jsonPath("$[0].id", containsString(a2.getId().toString())));
    }

    @Test
    public void GivenValidParams_WhenPostAppliances_ThenReturnResultsFromService_With200() throws Exception {
        Appliance beforeSave = new Appliance(null, "serial", "brand", "model", "status", "2020-01-01");
        Appliance afterSave = new Appliance(UUID.randomUUID(), "SERIAL", "BRAND", "MODEL", "status", "2020-01-01");
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        String beforeSaveJson = mapper.writeValueAsString(beforeSave);

        when(applianceService.addAppliance(ArgumentMatchers.any(Appliance.class))).thenReturn(afterSave);

        this.mockMvc.perform(post("/api/v1/appliances")
                        .content(beforeSaveJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", containsString(afterSave.getId().toString())));
    }

    @Test
    public void GivenInvalidParams_WhenPostAppliances_ThenThrowError400() throws Exception {
        this.mockMvc.perform(post("/api/v1/appliances")
                .content("{'date':'INVALID DATE'}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsString("Failed to parse input")));
    }

    @Test
    public void GivenValidParams_WhenPatchAppliances_ThenReturnResultsFromService_With200() throws Exception {
        UUID toBePatchedId = UUID.randomUUID();
        Appliance beforeSave = new Appliance(toBePatchedId, "serial", "brand", "model", "status", "2020-01-01");
        Appliance afterSave = new Appliance(toBePatchedId, "SERIAL", "BRAND", "MODEL", "status", "2020-01-01");
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        String beforeSaveJson = mapper.writeValueAsString(beforeSave);

        when(applianceService.patchAppliance(eq(toBePatchedId), ArgumentMatchers.any(Appliance.class))).thenReturn(afterSave);

        this.mockMvc.perform(patch("/api/v1/appliances/" + toBePatchedId.toString())
                .content(beforeSaveJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", containsString(afterSave.getId().toString())));
    }

    @Test
    public void GivenInvalidParams_WhenPatchAppliances_ThenThrowError400() throws Exception {
        UUID toBePatchedId = UUID.randomUUID();
        this.mockMvc.perform(patch("/api/v1/appliances/" + toBePatchedId)
                .content("{'date':'INVALID DATE'}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsString("Failed to parse input")));
    }

    @Test
    public void GivenUuid_WhenGetSingleAppliance_ThenReturnResultsFromService_With200() throws Exception {
        Appliance appliance = new Appliance(UUID.randomUUID(), "serial", "brand", "model", "status", "2020-01-01");

        when(applianceService.getAppliance(eq(appliance.getId()))).thenReturn(java.util.Optional.of(appliance));

        this.mockMvc.perform(get("/api/v1/appliances/" + appliance.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", containsString(appliance.getBrand())));
    }
}
