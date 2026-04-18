package com.ridesharing.observer;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAll(String message) {
        System.out.println("\n📢 Notification: " + message);
        for (Observer o : observers) {
            o.update(message);
        }
    }
}
