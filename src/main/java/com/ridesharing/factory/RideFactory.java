package factory;

import model.*;

public class RideFactory {
    private static int rideCounter = 1;

    public static Ride createRide(Commuter commuter, 
                                   String pickup, String drop) {
        Ride ride = new Ride(rideCounter++, commuter, pickup, drop);
        System.out.println("RideFactory: Created Ride #" + ride.getRideId() +
                           " for " + commuter.getName());
        return ride;
    }
}