package com.example.appliances.dao;

import com.example.appliances.models.Appliance;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public interface ApplianceDao {
    int create(UUID id, Appliance appliance);
    default int create(Appliance appliance) {
        UUID id = UUID.randomUUID();
        return create(id, appliance);
    }

    List<Appliance> all();

    void delete(UUID id);

    Optional<Appliance> find(UUID id);

    void update(UUID id, Appliance patchedAppliance) throws NoSuchElementException;
}
