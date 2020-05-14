package com.example.appliances.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Table(
    uniqueConstraints=@UniqueConstraint(
            columnNames={"serial_no", "brand", "model"})
)
@Entity
public class Appliance {
    // TODO: Add validators
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="serial_no")
    private String serialNo;
    @Column(name="brand")
    private String brand;
    @Column(name="model")
    private String model;
    @Column(name="status")
    private String status;
    @Column(name="purchase_date")
    private Date purchaseDate;

    public Appliance() {}
    public Appliance(@JsonProperty("id") UUID id,
                     @JsonProperty("serialNo") String serialNo,
                     @JsonProperty("brand") String brand,
                     @JsonProperty("model") String model,
                     @JsonProperty("status") String status,
                     @JsonProperty("purchaseDate") String purchaseDate) {
        this.id = id;
        this.serialNo = serialNo.toUpperCase();
        this.brand = brand.toUpperCase();
        this.model = model.toUpperCase();
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
