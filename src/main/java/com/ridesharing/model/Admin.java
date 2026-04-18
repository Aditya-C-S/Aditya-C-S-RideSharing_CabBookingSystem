package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
public class Admin extends User {

    public Admin() {}

    public Admin(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }
}
