package com.ridesharing.controller;

import com.ridesharing.model.Payment;
import com.ridesharing.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(@RequestParam Long rideId,
                                                    @RequestParam String method) {
        return ResponseEntity.ok(paymentService.initiatePayment(rideId, method));
    }

    @PostMapping("/{paymentId}/process")
    public ResponseEntity<Payment> processPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.processPayment(paymentId));
    }

    @PostMapping("/{paymentId}/fail")
    public ResponseEntity<Payment> failPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.failPayment(paymentId));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/ride/{rideId}")
    public ResponseEntity<Payment> getPaymentByRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(paymentService.getPaymentByRide(rideId));
    }
}
