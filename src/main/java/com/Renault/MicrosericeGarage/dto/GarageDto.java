package com.Renault.MicrosericeGarage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class GarageDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String telephone;

    @NotBlank
    @Email
    private String email;

    public GarageDto() {}

    public GarageDto(Long id, String name, String address, String city, String telephone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}