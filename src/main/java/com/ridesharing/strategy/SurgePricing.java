package strategy;

import singleton.SystemConfig;

public class SurgePricing implements PricingStrategy {
    @Override
    public double calculateFare(double distance) {
        SystemConfig config = SystemConfig.getInstance();
        double baseFare = config.getBaseFare() + (config.getPerKmRate() * distance);
        double surgeFare = baseFare * config.getSurgeMultiplier();
        System.out.println("Surge Pricing applied! Multiplier: " + 
                           config.getSurgeMultiplier() + "x");
        System.out.printf("Fare for %.1f km = ₹%.2f%n", distance, surgeFare);
        return surgeFare;
    }
}