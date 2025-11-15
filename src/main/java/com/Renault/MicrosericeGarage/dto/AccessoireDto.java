package com.Renault.MicrosericeGarage.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class AccessoireDto {

    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Double price;

    @NotBlank
    private String type;
    private Long vehicleId; // relation avec le véhicule

    public AccessoireDto() {
    }

    public AccessoireDto(Long id, String name, String description, Double price, String type, Long vehicleId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getprice() {
        return price;
    }

    public void setprice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
