package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
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
}