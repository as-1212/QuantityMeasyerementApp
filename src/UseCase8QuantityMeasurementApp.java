public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Convert to base unit (FEET)
    public double toBase(double value) {
        return value * toFeetFactor;
    }

    // Convert from base unit (FEET)
    public double fromBase(double baseValue) {
        return baseValue / toFeetFactor;
    }
}
