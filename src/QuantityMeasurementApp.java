public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    // Convert to base unit (KG)
    public double toBase(double value) {
        return value * toKgFactor;
    }

    // Convert from base unit (KG)
    public double fromBase(double baseValue) {
        return baseValue / toKgFactor;
    }
}