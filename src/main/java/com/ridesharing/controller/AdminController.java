package com.ridesharing.controller;

import com.ridesharing.model.*;
import com.ridesharing.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

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

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getReport() {
        return ResponseEntity.ok(adminService.generateReport());
    }
}
