package com.example.appliances.controllers;

import com.example.appliances.entities.Appliance;
import com.example.appliances.services.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping
@RestController
public class LandingController {

    @Autowired
    public LandingController() {}

    @GetMapping
    public String getGreetings() {
        return "Welcome";
    }
}
