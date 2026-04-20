package com.ridesharing.controller;

import com.ridesharing.model.*;
import com.ridesharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private CommuterRepository commuterRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody Map<String, String> body) {
    String name     = body.get("name");
    String email    = body.get("email");
    String phone    = body.get("phone");
    String password = body.get("password");
    String role     = body.get("role"); // "commuter" or "driver"
    String license  = body.get("licenseNumber"); // only for driver

    try {
        if ("driver".equals(role)) {
            Driver d = new Driver();
            d.setName(name); d.setEmail(email);
            d.setPhone(phone); d.setPassword(password);
            d.setLicenseNumber(license); d.setAvailable(true);
            driverRepository.save(d);
            return ResponseEntity.ok(Map.of("message", "Driver registered", "role", "driver"));
        } else {
            Commuter c = new Commuter();
            c.setName(name); c.setEmail(email);
            c.setPhone(phone); c.setPassword(password);
            commuterRepository.save(c);
            return ResponseEntity.ok(Map.of("message", "Commuter registered", "role", "commuter"));
        }
    } catch (Exception e) {
        return ResponseEntity.status(500).body(Map.of("error", "Email already exists or invalid data"));
    }
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email    = body.get("email");
        String password = body.get("password");
        String role     = body.get("role"); // "commuter", "driver", "admin"

        try {
            switch (role.toLowerCase()) {
                case "commuter" -> {
                    Commuter u = commuterRepository.findByEmail(email);
                    if (u != null && u.getPassword().equals(password))
                        return ResponseEntity.ok(Map.of("id", u.getId(), "name", u.getName(), "role", "commuter"));
                }
                case "driver" -> {
                    Driver u = driverRepository.findByEmail(email);
                    if (u != null && u.getPassword().equals(password))
                        return ResponseEntity.ok(Map.of("id", u.getId(), "name", u.getName(), "role", "driver"));
                }
                case "admin" -> {
                    Admin u = adminRepository.findByEmail(email);
                    if (u != null && u.getPassword().equals(password))
                        return ResponseEntity.ok(Map.of("id", u.getId(), "name", u.getName(), "role", "admin"));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }

        return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
    }
}