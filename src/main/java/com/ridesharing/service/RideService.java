package com.ridesharing.service;

import com.ridesharing.model.*;
import com.ridesharing.model.Ride.RideStatus;
import com.ridesharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ridesharing.repository.PlaceDistanceRepository;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CommuterRepository commuterRepository;

    @Autowired
    private PlaceDistanceRepository placeDistanceRepository;

    // Request a new ride
    public Ride requestRide(Long commuterId, String pickup, String drop) {
        Commuter commuter = commuterRepository.findById(commuterId)
            .orElseThrow(() -> new RuntimeException("Commuter not found"));

        Ride ride = new Ride(commuter, pickup, drop);
        return rideRepository.save(ride);
    }

    // Assign nearest available driver
    public Ride assignDriver(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Ride not found"));

        List<Driver> availableDrivers = driverRepository.findByAvailable(true);
        if (availableDrivers.isEmpty()) {
            ride.setStatus(RideStatus.CANCELLED);
            return rideRepository.save(ride);
        }

        Driver driver = availableDrivers.get(0);
        driver.setAvailable(false);
        driverRepository.save(driver);

        ride.setDriver(driver);
        ride.setStatus(RideStatus.DRIVER_ASSIGNED);
        return rideRepository.save(ride);
    }

    // Start ride
    public Ride startRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Ride not found"));
        ride.setStatus(RideStatus.IN_PROGRESS);
        return rideRepository.save(ride);
    }

    // Accept ride (driver confirms)
    public Ride acceptRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Ride not found"));
        // Only accept if a driver has been assigned
        if (ride.getStatus() != RideStatus.DRIVER_ASSIGNED) {
            throw new RuntimeException("Ride is not in DRIVER_ASSIGNED state");
        }
        ride.setStatus(RideStatus.IN_PROGRESS);
        return rideRepository.save(ride);
    }

    // End ride
    public Ride endRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Ride not found"));

        double fare = calculateFare(ride.getPickupLocation(), ride.getDropLocation());
        ride.setFare(fare);
        ride.setStatus(RideStatus.COMPLETED);

        // Free up driver
        if (ride.getDriver() != null) {
            Driver driver = ride.getDriver();
            driver.setAvailable(true);
            driverRepository.save(driver);
        }
        return rideRepository.save(ride);
    }
    // Get rides by driver
    public List<Ride> getRidesByDriver(Long driverId) {
        return rideRepository.findByDriverId(driverId);
    }

    // Cancel ride
    public Ride cancelRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getDriver() != null) {
            Driver driver = ride.getDriver();
            driver.setAvailable(true);
            driverRepository.save(driver);
        }
        ride.setStatus(RideStatus.CANCELLED);
        return rideRepository.save(ride);
    }

    // Fare calculation
    private double calculateFare(String pickup, String drop) {
        double baseFare = 50.0;
        double perKmRate = 12.0;
        double km = placeDistanceRepository.findDistance(pickup, drop)
            .map(p -> p.getDistanceKm())
            .orElse(10.0); // fallback if pair not found
        return baseFare + (perKmRate * km);
    }

    // Get all rides
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    // Get rides by commuter
    public List<Ride> getRidesByCommuter(Long commuterId) {
        return rideRepository.findByCommuterId(commuterId);
    }

    // Get rides by status
    public List<Ride> getRidesByStatus(RideStatus status) {
        return rideRepository.findByStatus(status);
    }

    // Get ride by id
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Ride not found"));
    }

    public List<String> getPlaces() {
        return placeDistanceRepository.findAllPlaceNames();
    }

    public double getDistance(String pickup, String drop) {
        return placeDistanceRepository.findDistance(pickup, drop)
            .map(p -> p.getDistanceKm())
            .orElse(10.0); // fallback if pair not found
    }
}