package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
public class Commuter extends User {
    public Commuter() {}
    public Commuter(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }
    @Override public void setName(String name) { super.setName(name); }
    @Override public void setEmail(String email) { super.setEmail(email); }
    @Override public void setPhone(String phone) { super.setPhone(phone); }
    @Override public void setPassword(String password) { super.setPassword(password); }
}