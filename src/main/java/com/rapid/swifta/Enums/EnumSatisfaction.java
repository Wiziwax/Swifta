package com.rapid.swifta.Enums;

public enum EnumSatisfaction {

    SATISFIED(5),
    FAIRLY_SATISFIED(4),
    AVERAGE(3),
    POOR_SERVICE(2),
    DISAPPOINTED(1);

    private final int numericRating;

    EnumSatisfaction(int numericRating) {
        this.numericRating = numericRating;
    }

    public int getNumericValue() {
        return numericRating;
    }

    public static EnumSatisfaction fromNumericValue(int numericValue) {
        for (EnumSatisfaction value : EnumSatisfaction.values()) {
            if (value.getNumericValue() == numericValue) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid numeric value: " + numericValue);
    }

    public static EnumSatisfaction fromString(String text) {
        for (EnumSatisfaction value : EnumSatisfaction.values()) {
            if (value.name().equalsIgnoreCase(text)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid text: " + text);
    }

    public static int getNumericValueFromString(String text) {
        for (EnumSatisfaction value : EnumSatisfaction.values()) {
            if (value.name().equalsIgnoreCase(text)) {
                return value.getNumericValue();
            }
        }
        throw new IllegalArgumentException("Invalid text: " + text);
    }

}
