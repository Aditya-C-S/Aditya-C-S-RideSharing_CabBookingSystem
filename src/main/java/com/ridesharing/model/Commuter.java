package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
public class Commuter extends User {
    public Commuter() {}
    public Commuter(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }
}