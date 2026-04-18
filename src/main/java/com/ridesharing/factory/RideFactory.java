package com.ridesharing.factory;

import com.ridesharing.model.Commuter;
import com.ridesharing.model.Ride;
import org.springframework.stereotype.Component;

@Component
public class RideFactory {

    public Ride createRide(Commuter commuter, String pickup, String drop) {
        Ride ride = new Ride(commuter, pickup, drop);
        System.out.println("RideFactory: Created ride for " + commuter.getName());
        return ride;
    }
}
