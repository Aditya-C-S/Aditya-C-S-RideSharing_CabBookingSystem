package com.ridesharing.service;

import com.ridesharing.model.*;
import com.ridesharing.model.Payment.PaymentStatus;
import com.ridesharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RideRepository rideRepository;

    // Initiate payment
    public Payment initiatePayment(Long rideId, String method) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus() != Ride.RideStatus.COMPLETED) {
            throw new RuntimeException("Ride is not completed yet");
        }

        Payment payment = new Payment(ride, ride.getFare(), method);
        return paymentRepository.save(payment);
    }

    // Process payment
    public Payment processPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Simulate success
        payment.setStatus(PaymentStatus.SUCCESS);
        return paymentRepository.save(payment);
    }

    // Fail payment
    public Payment failPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.FAILED);
        return paymentRepository.save(payment);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get payment by ride
    public Payment getPaymentByRide(Long rideId) {
        return paymentRepository.findByRideId(rideId);
    }
}