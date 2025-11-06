package com.Renault.MicrosericeGarage.dto;

public class VehicleDto {
    private String brand;
    private int year;
    private String fuelType;
    private Long garageId;

    public VehicleDto(){}

    public VehicleDto(String brand, int year, String fuelType, Long garageId) {
        this.brand = brand;
        this.year = year;
        this.fuelType = fuelType;
        this.garageId = garageId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getGarageId() {
        return garageId;
    }

    public void setGarageId(Long garageId) {
        this.garageId = garageId;
    }
}
