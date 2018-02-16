package com.rafibaum.metal.geometry;

/**
 * Angle is a class which represents unwrapped geometric angles in either radians or in degrees.
 */
public class Angle {

    /**
     * The AngleUnit enum is used to specify whether an angle is in radians or degrees.
     */
    enum AngleUnit {
        DEGREES,
        RADIANS
        }

    private double degrees;

    /**
     * This constructor takes in an angle with a specified unit as its value.
     * @param unit the unit of the angle
     * @param value the angle's measure in the specified unit
     */
    public Angle(AngleUnit unit, double value) {
        if(unit == AngleUnit.RADIANS) {
            value = (value / Math.PI) * 180.0; //Converts a radian angle to degrees
        }

        this.degrees = value;
    }

    /**
     * This constructor takes in an angle in degrees as its value.
     * @param degrees the angle's measure in degrees.
     */
    public Angle(double degrees) {
        this.degrees = degrees;
    }

    /**
     * Returns the value of the angle in degrees.
     * @return the value of the angle in degrees
     */
    public double toDegrees() {
        return degrees;
    }

    /**
     * Returns the value of the angle in radians.
     * @return the value of the angle in radians.
     */
    public double toRadians() {
        return (degrees/180.0) * Math.PI;
    }

    /**
     * Adds two angles together and returns the result.
     * @param angle the angle to be added to this one
     * @return the sum of the two angles
     */
    public Angle add(Angle angle) {
        return new Angle(degrees + angle.toDegrees());
    }

    /**
     * Subtracts another angle from this angle (as this angle - parameter = result) and returns the result.
     * @param angle the angle to be subtracted from this one
     * @return the difference of the two angles (as this angle - parameter = result)
     */
    public Angle subtract(Angle angle) {
        return new Angle(degrees - angle.toDegrees());
    }

    /**
     * Multiplies this angle by a scalar quantity and returns the result.
     * @param scalar the scalar multiplier of the angle
     * @return the product of the angle and the scalar
     */
    public Angle multiply(double scalar) {
        return new Angle(degrees * scalar);
    }

    /**
     * Divides this angle by a dividend and returns the result.
     * @param dividend the dividend to divide this angle by
     * @return the quotient of the angle and the dividend
     */
    public Angle divide(double dividend) {
        return new Angle(degrees / dividend);
    }

}
