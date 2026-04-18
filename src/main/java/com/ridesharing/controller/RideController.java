package com.ridesharing.controller;

import com.ridesharing.model.*;
import com.ridesharing.service.RideService;
import com.ridesharing.service.PaymentService;
import com.ridesharing.repository.PlaceDistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PlaceDistanceRepository placeDistanceRepository;

    @PostMapping("/request")
    public ResponseEntity<Ride> requestRide(@RequestParam Long commuterId,
                                             @RequestParam String pickup,
                                             @RequestParam String drop) {
        Ride ride = rideService.requestRide(commuterId, pickup, drop);
        return ResponseEntity.ok(ride);
    }

    @PostMapping("/{rideId}/assign-driver")
    public ResponseEntity<Ride> assignDriver(@PathVariable Long rideId) {
        Ride ride = rideService.assignDriver(rideId);
        return ResponseEntity.ok(ride);
    }

    @PostMapping("/{rideId}/start")
    public ResponseEntity<Ride> startRide(@PathVariable Long rideId) {
        Ride ride = rideService.startRide(rideId);
        return ResponseEntity.ok(ride);
    }

    @PostMapping("/{rideId}/end")
    public ResponseEntity<Ride> endRide(@PathVariable Long rideId) {
        Ride ride = rideService.endRide(rideId);
        return ResponseEntity.ok(ride);
    }

    @PostMapping("/{rideId}/cancel")
    public ResponseEntity<Ride> cancelRide(@PathVariable Long rideId) {
        Ride ride = rideService.cancelRide(rideId);
        return ResponseEntity.ok(ride);
    }

    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        return ResponseEntity.ok(rideService.getAllRides());
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.getRideById(rideId));
    }

    @GetMapping("/commuter/{commuterId}")
    public ResponseEntity<List<Ride>> getRidesByCommuter(@PathVariable Long commuterId) {
        return ResponseEntity.ok(rideService.getRidesByCommuter(commuterId));
    }

    @GetMapping("/places")
    public ResponseEntity<List<String>> getPlaces() {
        return ResponseEntity.ok(rideService.getPlaces());
    }

    @GetMapping("/fare-estimate")
    public ResponseEntity<?> fareEstimate(@RequestParam String pickup,
                                        @RequestParam String drop,
                                        @RequestParam(defaultValue = "50") double baseFare,
                                        @RequestParam(defaultValue = "12") double perKm) {
        double km = placeDistanceRepository.findDistance(pickup, drop)
            .map(p -> p.getDistanceKm())
            .orElse(10.0);
        double fare = baseFare + (perKm * km);
        return ResponseEntity.ok(java.util.Map.of("distanceKm", km, "fare", fare));
    }
}
