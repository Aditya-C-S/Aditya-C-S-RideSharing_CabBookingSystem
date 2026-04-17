package com.ridesharing.repository;

import com.ridesharing.model.Ride;
import com.ridesharing.model.Ride.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByStatus(RideStatus status);
    List<Ride> findByCommuterId(Long commuterId);
    List<Ride> findByDriverId(Long driverId);
}