package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Commuter commuter;

    @ManyToOne
    private Driver driver;

    private String pickupLocation;
    private String dropLocation;
    private double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    public enum RideStatus {
        REQUESTED, DRIVER_ASSIGNED, IN_PROGRESS, COMPLETED, CANCELLED
    }

    public Ride() {}
    public Ride(Commuter commuter, String pickupLocation, String dropLocation) {
        this.commuter = commuter;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.status = RideStatus.REQUESTED;
        this.fare = 0.0;
    }

    public Long getId() { return id; }
    public Commuter getCommuter() { return commuter; }
    public Driver getDriver() { return driver; }
    public String getPickupLocation() { return pickupLocation; }
    public String getDropLocation() { return dropLocation; }
    public double getFare() { return fare; }
    public RideStatus getStatus() { return status; }

    public void setDriver(Driver driver) { this.driver = driver; }
    public void setFare(double fare) { this.fare = fare; }
    public void setStatus(RideStatus status) { this.status = status; }
}