package com.example.appliances.controllers;

import com.example.appliances.entities.Appliance;
import com.example.appliances.services.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("api/v1/appliances")
@RestController
public class ApplianceController {

    private final ApplianceService applianceService;

    @Autowired
    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    @PostMapping
    public void addAppliance(@RequestBody Appliance appliance) {
        applianceService.addAppliance(appliance);
    }

    @GetMapping
    public Iterable<Appliance> getAppliances() {
        return applianceService.getAppliances();
    }

    @GetMapping("{id}")
    public Appliance getAppliance(@PathVariable("id") UUID id) {
        return applianceService.getAppliance(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAppliance(@PathVariable("id") UUID id) {
        applianceService.deleteAppliance(id);
    }

    @PatchMapping("{id}")
    public void updateAppliance(@PathVariable(value = "id") UUID id,
                                @RequestBody Appliance appliance) {
        applianceService.patchAppliance(id, appliance);
    }
}
