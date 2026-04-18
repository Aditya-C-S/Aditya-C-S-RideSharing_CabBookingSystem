package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String model;
    private String type;
    private boolean active;

    public Vehicle() {}

    public Vehicle(String licensePlate, String model, String type) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.type = type;
        this.active = true;
    }

    public Long getId() { return id; }
    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public void setModel(String model) { this.model = model; }
    public void setType(String type) { this.type = type; }
}
