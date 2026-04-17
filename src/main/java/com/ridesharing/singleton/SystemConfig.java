package singleton;

public class SystemConfig {
    private static SystemConfig instance;
    private double surgeMultiplier;
    private double baseFare;
    private double perKmRate;

    private SystemConfig() {
        this.surgeMultiplier = 1.0;
        this.baseFare = 50.0;
        this.perKmRate = 12.0;
    }

    public static SystemConfig getInstance() {
        if (instance == null) {
            instance = new SystemConfig();
        }
        return instance;
    }

    public double getSurgeMultiplier() { return surgeMultiplier; }
    public void setSurgeMultiplier(double multiplier) { this.surgeMultiplier = multiplier; }
    public double getBaseFare() { return baseFare; }
    public double getPerKmRate() { return perKmRate; }

    public void displayConfig() {
        System.out.println("=== System Config ===");
        System.out.println("Base Fare     : ₹" + baseFare);
        System.out.println("Per KM Rate   : ₹" + perKmRate);
        System.out.println("Surge Multi   : " + surgeMultiplier + "x");
    }
}