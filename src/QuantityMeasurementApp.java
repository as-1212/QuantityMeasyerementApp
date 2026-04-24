public class QuantityMeasurementApp {

    // ===== ENUM =====
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // ===== Quantity Class =====
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
        private static final double EPS = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ===== UC6 method =====
        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        // ===== UC7 method =====
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double totalFeet = this.toFeet() + other.toFeet();
            double result = targetUnit.fromFeet(totalFeet);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toFeet() - other.toFeet()) < EPS;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(a.add(b, LengthUnit.FEET));      // 2 FEET
        System.out.println(a.add(b, LengthUnit.INCH));      // 24 INCH
        System.out.println(a.add(b, LengthUnit.YARD));      // 0.6667 YARD
    }
}