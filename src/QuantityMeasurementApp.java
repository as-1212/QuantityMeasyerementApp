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
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ===== ADD METHOD (Instance) =====
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            double sumFeet = this.toFeet() + other.toFeet();
            double result = this.unit.fromFeet(sumFeet);

            return new QuantityLength(result, this.unit);
        }

        // ===== STATIC ADD (Overloaded) =====
        public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = q1.toFeet() + q2.toFeet();
            double result = targetUnit.fromFeet(sumFeet);

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

        QuantityLength f1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength i1 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(f1.add(i1)); // 2 FEET
        System.out.println(i1.add(f1)); // 24 INCH

        QuantityLength y = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength f = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(y.add(f)); // 2 YARD
    }
}