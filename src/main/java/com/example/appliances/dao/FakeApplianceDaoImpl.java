package com.example.appliances.dao;

import com.example.appliances.models.Appliance;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.*;

// TODO: Convert to Postgresql
@Repository("fakeDao")
public class FakeApplianceDaoImpl implements ApplianceDao {

    private static List<Appliance> fakeDb = new ArrayList<>();

    @Override
    public int create(UUID id, Appliance appliance) {
        fakeDb.add(
                new Appliance(
                        id,
                        appliance.getSerialNo(),
                        appliance.getBrand(),
                        appliance.getModel(),
                        appliance.getStatus(),
                        appliance.getPurchaseDate().toString()
                )
        );
        return 1;
    }

    @Override
    public List<Appliance> all() {
        return fakeDb;
    }

    @Override
    public void delete(UUID id) {
        fakeDb.removeIf(appliance -> (appliance.getId().equals(id)));
    }

    @Override
    public Optional<Appliance> find(UUID id) {
        return fakeDb.stream()
                .filter(appliance -> appliance.getId().equals(id))
                .findFirst();
    }

    @Override
    public void update(UUID id, Appliance patchedAppliance) throws NoSuchElementException {
        fakeDb.stream()
                .filter(appliance -> appliance.getId().equals(id))
                .peek(appliance -> {
                    BeanUtils.copyProperties(patchedAppliance, appliance, "id", null);
                })
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
