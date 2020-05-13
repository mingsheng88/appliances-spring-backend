package com.example.appliances.dao;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplianceDao {

    // => Must have create, returning Appliance instance
    // => Must have update, returning Optional<Appliance> object
    // => Must have delete, void method

    // Q: Split out reads into a Repository?
    // => Must have find, returning Optional<Appliance> object
    // => Must have all, returning List<Appliance>

}
