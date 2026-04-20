package com.ridesharing.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String model;
    private String type;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonIgnoreProperties({"password", "licenseNumber", "available"})
    private Driver driver;

    public Vehicle() {}

    public Long getId() { return id; }
    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public boolean isActive() { return active; }
    public Driver getDriver() { return driver; }

    public void setActive(boolean active) { this.active = active; }
    public void setLicensePlate(String lp) { this.licensePlate = lp; }
    public void setModel(String model) { this.model = model; }
    public void setType(String type) { this.type = type; }
    public void setDriver(Driver driver) { this.driver = driver; }
}