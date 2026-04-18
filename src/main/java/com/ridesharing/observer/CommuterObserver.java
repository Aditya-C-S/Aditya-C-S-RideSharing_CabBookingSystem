package com.ridesharing.observer;

import com.ridesharing.model.Commuter;

public class CommuterObserver implements Observer {
    private Commuter commuter;

    public CommuterObserver(Commuter commuter) {
        this.commuter = commuter;
    }

    @Override
    public void update(String message) {
        System.out.println("  → Commuter [" + commuter.getName() + "] notified: " + message);
    }
}
