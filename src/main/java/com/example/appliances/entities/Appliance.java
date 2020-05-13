package com.example.appliances.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.util.UUID;

// TODO: Can I use Jackson here?
@Entity
public class Appliance {
    // TODO: Add validators
    @Id
    @GeneratedValue
    private UUID id;

    @Column private String serialNo;
    @Column private String brand;
    @Column private String model;
    @Column private String status;
    @Column private Date purchaseDate;

    public Appliance(@JsonProperty("id") UUID id,
                     @JsonProperty("serialNo") String serialNo,
                     @JsonProperty("brand") String brand,
                     @JsonProperty("model") String model,
                     @JsonProperty("status") String status,
                     @JsonProperty("purchaseDate") String purchaseDate) {
        this.id = id;
        this.serialNo = serialNo;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.purchaseDate = Date.valueOf(purchaseDate);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
