package com.ridesharing.controller;

import com.ridesharing.model.*;
import com.ridesharing.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ridesharing.model.Vehicle;
import com.ridesharing.repository.VehicleRepository;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private com.ridesharing.repository.DriverRepository driverRepository;

    @GetMapping("/commuters")
    public ResponseEntity<List<Commuter>> getAllCommuters() {
        return ResponseEntity.ok(adminService.getAllCommuters());
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(adminService.getAllDrivers());
    }

    @PostMapping("/commuters")
    public ResponseEntity<Commuter> addCommuter(@RequestBody Commuter commuter) {
        return ResponseEntity.ok(adminService.addCommuter(commuter));
    }

    @PostMapping("/drivers")
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(adminService.addDriver(driver));
    }

    @DeleteMapping("/commuters/{id}")
    public ResponseEntity<Void> deleteCommuter(@PathVariable Long id) {
        adminService.deleteCommuter(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        adminService.deleteDriver(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleRepository.findAll());
    }

    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Map<String, Object> body) {
        Vehicle v = new Vehicle();
        v.setLicensePlate((String) body.get("licensePlate"));
        v.setModel((String) body.get("model"));
        v.setType((String) body.get("type"));
        v.setActive(true);
        Object driverIdObj = body.get("driverId");
        if (driverIdObj != null && !driverIdObj.toString().isEmpty()) {
            Long driverId = Long.valueOf(driverIdObj.toString());
            driverRepository.findById(driverId).ifPresent(v::setDriver);
        }
        return ResponseEntity.ok(vehicleRepository.save(v));
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vehicles/driver/{driverId}")
    public ResponseEntity<?> getVehicleByDriver(@PathVariable Long driverId) {
        return vehicleRepository.findByDriverId(driverId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getReport() {
        return ResponseEntity.ok(adminService.generateReport());
    }
}
