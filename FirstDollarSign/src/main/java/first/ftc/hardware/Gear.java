package first.ftc.hardware;

public enum Gear {
    FAST(0.75),
    MED(0.6),
    SLOW(0.5);
    private final double multiplier;
    Gear(double multiplier) {
        this.multiplier = multiplier;
    }
    public double speed() {
        return this.multiplier;
    }
}
