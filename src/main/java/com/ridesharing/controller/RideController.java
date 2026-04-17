package controller;

import model.*;
import model.Payment.PaymentMethod;
import service.*;

public class RideController {
    private RideService rideService;
    private PaymentService paymentService;
    private AdminService adminService;

    public RideController() {
        this.rideService = new RideService();
        this.paymentService = new PaymentService();
        this.adminService = new AdminService(rideService, paymentService);
    }

    // Full ride booking flow
    public void bookRide(Commuter commuter, String pickup, String drop, PaymentMethod method) {
        System.out.println("\n========================================");
        System.out.println("        NEW RIDE BOOKING");
        System.out.println("========================================");

        // Step 1: Request ride
        Ride ride = rideService.requestRide(commuter, pickup, drop);

        // Step 2: Assign driver
        boolean assigned = rideService.assignDriver(ride);
        if (!assigned) return;

        // Step 3: Driver arriving
        rideService.driverArriving(ride);

        // Step 4: Start ride
        rideService.startRide(ride);

        // Step 5: End ride
        rideService.endRide(ride);

        // Step 6: Process payment
        Payment payment = paymentService.initiatePayment(ride, method);
        boolean paid = paymentService.processPayment(payment);

        // Step 7: Retry if failed
        if (!paid) {
            paymentService.retryPayment(payment);
        }

        // Step 8: Free up driver
        if (ride.getDriver() != null) {
            ride.getDriver().setAvailable(true);
        }

        System.out.println("========================================\n");
    }

    // Cancel a ride
    public void cancelRide(Commuter commuter, String pickup, String drop) {
        System.out.println("\n========================================");
        System.out.println("        CANCELLING RIDE");
        System.out.println("========================================");
        Ride ride = rideService.requestRide(commuter, pickup, drop);
        rideService.assignDriver(ride);
        rideService.cancelRide(ride);
        System.out.println("========================================\n");
    }

    // View ride history for a commuter
    public void viewRideHistory(Commuter commuter) {
        System.out.println("\n===== RIDE HISTORY: " + commuter.getName() + " =====");
        if (commuter.getRideHistory().isEmpty()) {
            System.out.println("No rides yet.");
            return;
        }
        for (Ride r : commuter.getRideHistory()) r.displayInfo();
        System.out.println("==========================================\n");
    }

    // Give feedback
    public void giveFeedback(Commuter commuter, Ride ride, double rating) {
        if (ride.getStatus() != Ride.RideStatus.COMPLETED) {
            System.out.println("✘ Can only give feedback for completed rides.");
            return;
        }
        ride.getDriver().updateRating(rating);
        System.out.println("✔ Feedback submitted! Rating: " + rating + 
                           " for driver " + ride.getDriver().getName());
    }

    // Admin actions
    public void showSystemReport() { adminService.generateReport(); }
    public void monitorRides() { adminService.monitorAllRides(); }

    // Getters for services (needed for Main)
    public RideService getRideService() { return rideService; }
    public PaymentService getPaymentService() { return paymentService; }
}