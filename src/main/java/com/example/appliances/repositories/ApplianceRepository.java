package com.example.appliances.repositories;

import com.example.appliances.entities.Appliance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ApplianceRepository extends CrudRepository<Appliance, UUID> {
    List<Appliance> findAllBySerialNoLikeOrBrandLikeOrModelLikeOrStatusLike(
            String serialNo, String brand, String model, String status
    );
}
