package com.ridesharing.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver extends User {
    private String licenseNumber;
    private boolean available;

    public Driver() {}
    public Driver(String name, String email, String phone, 
                  String password, String licenseNumber) {
        super(name, email, phone, password);
        this.licenseNumber = licenseNumber;
        this.available = true;
    }

    public String getLicenseNumber() { return licenseNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setLicenseNumber(String licenseNumber) { 
        this.licenseNumber = licenseNumber; 
    }

    @Override public void setEmail(String email) { super.setEmail(email); }
    @Override public void setPhone(String phone) { super.setPhone(phone); }
    @Override public void setPassword(String password) { super.setPassword(password); }
    @Override public void setName(String name) { super.setName(name); }
}