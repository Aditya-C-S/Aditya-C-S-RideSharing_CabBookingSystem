package model;

public class Vehicle {
    private int vehicleId;
    private String licensePlate;
    private String model;
    private String type; // Sedan, SUV, Auto
    private boolean isActive;

    public Vehicle(int vehicleId, String licensePlate, String model, String type) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.model = model;
        this.type = type;
        this.isActive = true;
    }

    public int getVehicleId() { return vehicleId; }
    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }

    public void displayInfo() {
        System.out.println("--- Vehicle ---");
        System.out.println("ID: " + vehicleId);
        System.out.println("Plate: " + licensePlate);
        System.out.println("Model: " + model);
        System.out.println("Type: " + type);
    }
}