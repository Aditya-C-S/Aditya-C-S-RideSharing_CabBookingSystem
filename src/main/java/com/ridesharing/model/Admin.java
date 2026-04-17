package model;

public class Admin extends User {

    public Admin(int userId, String name, String email, String phone, String password) {
        super(userId, name, email, phone, password);
    }

    @Override
    public void displayInfo() {
        System.out.println("--- Admin ---");
        System.out.println("ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
    }
}