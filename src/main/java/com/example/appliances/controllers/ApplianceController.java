package com.example.appliances.controllers;

import com.example.appliances.entities.Appliance;
import com.example.appliances.services.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public Appliance addAppliance(@RequestBody Appliance appliance) {
        return applianceService.addAppliance(appliance);
    }

    @GetMapping
    public Iterable<Appliance> getAppliances(@RequestParam(value = "query", required = false) String query) {
        return applianceService.getAppliances(query);
    }

    @GetMapping("{id}")
    public Appliance getAppliance(@PathVariable("id") UUID id) throws ChangeSetPersister.NotFoundException {
        return applianceService.getAppliance(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAppliance(@PathVariable("id") UUID id) {
        applianceService.deleteAppliance(id);
    }

    @PatchMapping("{id}")
    public Appliance updateAppliance(@PathVariable(value = "id") UUID id,
                                @RequestBody Appliance appliance) {
        return applianceService.patchAppliance(id, appliance);
    }
}
