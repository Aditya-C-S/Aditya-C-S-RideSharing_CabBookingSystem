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