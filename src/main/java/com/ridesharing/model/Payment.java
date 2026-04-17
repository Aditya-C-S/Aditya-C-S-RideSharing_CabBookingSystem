package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ride ride;

    private double amount;
    private String method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }

    public Payment() {}
    public Payment(Ride ride, double amount, String method) {
        this.ride = ride;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
    }

    public Long getId() { return id; }
    public Ride getRide() { return ride; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
}