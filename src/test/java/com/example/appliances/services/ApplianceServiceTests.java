package com.example.appliances.services;

import com.example.appliances.repositories.ApplianceRepository;
import com.example.appliances.entities.Appliance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ApplianceServiceTests {

    @Mock
    ApplianceRepository applianceRepository;

    @InjectMocks
    ApplianceService applianceService;

    @Test
    public void GivenNoQueryString_WhenGetAppliances_ThenReturnAllAppliances() throws Exception {
        Iterable<Appliance> result = applianceService.getAppliances(null);

        Mockito.verify(applianceRepository).findAll();
    }

    @Test
    public void GivenAQueryString_WhenGetAppliances_ThenReturnAllAppliances() throws Exception {
        String query = "q";

        Iterable<Appliance> result = applianceService.getAppliances(query);

        String expectedQuery = "%q%";
        Mockito.verify(applianceRepository).findAllBySerialNoLikeOrBrandLikeOrModelLikeOrStatusLike(expectedQuery, expectedQuery, expectedQuery, expectedQuery);
    }

    @Test
    public void GiveADifferentAppliance_WhenPatchAppliance_ThenApplianceIsPersisted() throws Exception {
        Appliance a1 = new Appliance(UUID.randomUUID(), "serial", "brand", "model", "status", "2020-01-01");
        Appliance a2 = new Appliance(null, "serial2", "brand2", "model2", "status2", "2020-02-02");

        Mockito.when(applianceRepository.findById(a1.getId())).thenReturn(java.util.Optional.of(a1));

        applianceService.patchAppliance(a1.getId(), a2);

        // TODO: How to assert inner changes?
        Mockito.verify(applianceRepository).save(a1);
    }
}
