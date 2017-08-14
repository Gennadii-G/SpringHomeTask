package ru.epam.spring.hometask.domain;

/**
 * @author Yuriy_Tkach
 */
public enum EventRating {

    LOW(0.8),

    MID(1.0),

    HIGH(1.2);

    private double coefficient;

    EventRating(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
