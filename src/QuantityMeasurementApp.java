public class QuantityMeasurementApp {

    // ===== ENUM with Extended Units =====
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);
        // 1 cm = 0.393701 inch → convert to feet → divide by 12

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // ===== Generic Quantity Class =====
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // ===== Main Method =====
    public static void main(String[] args) {

        System.out.println(
                new QuantityLength(1.0, LengthUnit.YARD)
                        .equals(new QuantityLength(3.0, LengthUnit.FEET))
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.YARD)
                        .equals(new QuantityLength(36.0, LengthUnit.INCH))
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.CENTIMETER)
                        .equals(new QuantityLength(0.393701, LengthUnit.INCH))
        );
    }
}