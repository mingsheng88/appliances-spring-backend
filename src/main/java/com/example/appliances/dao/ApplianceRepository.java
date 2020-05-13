package com.example.appliances.dao;

import com.example.appliances.entities.Appliance;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ApplianceRepository extends CrudRepository<Appliance, UUID> {}
