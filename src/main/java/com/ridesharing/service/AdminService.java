package com.ridesharing.service;

import com.ridesharing.model.*;
import com.ridesharing.model.Payment.PaymentStatus;
import com.ridesharing.model.Ride.RideStatus;
import com.ridesharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AdminService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CommuterRepository commuterRepository;

    @Autowired
    private DriverRepository driverRepository;

    // Get all commuters
    public List<Commuter> getAllCommuters() {
        return commuterRepository.findAll();
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Add commuter
    public Commuter addCommuter(Commuter commuter) {
        return commuterRepository.save(commuter);
    }

    // Add driver
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // Delete user
    public void deleteCommuter(Long id) {
        commuterRepository.deleteById(id);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    // Generate report
    public Map<String, Object> generateReport() {
        List<Ride> allRides = rideRepository.findAll();
        List<Payment> allPayments = paymentRepository.findAll();

        long completed = allRides.stream()
            .filter(r -> r.getStatus() == RideStatus.COMPLETED).count();
        long cancelled = allRides.stream()
            .filter(r -> r.getStatus() == RideStatus.CANCELLED).count();
        double totalRevenue = allPayments.stream()
            .filter(p -> p.getStatus() == PaymentStatus.SUCCESS)
            .mapToDouble(Payment::getAmount).sum();

        Map<String, Object> report = new LinkedHashMap<>();
        report.put("totalRides", allRides.size());
        report.put("completedRides", completed);
        report.put("cancelledRides", cancelled);
        report.put("totalRevenue", totalRevenue);
        report.put("totalPayments", allPayments.size());

        return report;
    }
}