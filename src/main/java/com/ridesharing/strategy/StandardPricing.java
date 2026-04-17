package strategy;

import singleton.SystemConfig;

public class StandardPricing implements PricingStrategy {
    @Override
    public double calculateFare(double distance) {
        SystemConfig config = SystemConfig.getInstance();
        double fare = config.getBaseFare() + (config.getPerKmRate() * distance);
        System.out.println("Standard Pricing applied.");
        System.out.printf("Fare for %.1f km = ₹%.2f%n", distance, fare);
        return fare;
    }
}