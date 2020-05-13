package com.example.appliances.services;

import com.example.appliances.dao.ApplianceDao;
import com.example.appliances.models.Appliance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplianceService {
    private final ApplianceDao applianceDao;

    @Autowired
    public ApplianceService(@Qualifier("fakeDao") ApplianceDao applianceDao) {
        this.applianceDao = applianceDao;
    }

    public int addAppliance(Appliance appliance) {
        return applianceDao.create(appliance);
    }

    public List<Appliance> getAppliances() {
        return applianceDao.all();
    }

    public void deleteAppliance(UUID id) {
        applianceDao.delete(id);
    }

    public Optional<Appliance> getAppliance(UUID id) {
        return applianceDao.find(id);
    }

    public void patchAppliance(UUID id, Appliance patchedAppliance) {
        applianceDao.update(id, patchedAppliance);
    }
}
