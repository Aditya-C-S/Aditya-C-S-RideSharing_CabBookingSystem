package com.ridesharing.observer;

import com.ridesharing.model.Driver;

public class DriverObserver implements Observer {
    private Driver driver;

    public DriverObserver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void update(String message) {
        System.out.println("  → Driver [" + driver.getName() + "] notified: " + message);
    }
}
