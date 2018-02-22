package com.rafibaum.metal.geometry;

import com.rafibaum.metal.utils.MetalConfigurationException;

/**
 * Angle is a class which represents unwrapped geometric angles in either radians or in degrees.
 * Metal primarily uses "navigation" style angles where zero represents a forward angle and angles
 * increase in the clockwise direction from there.
 */
public class Angle {

    public static final Angle ZERO = new Angle(0.0);

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

    /**
     * This method wraps an angle so that it's within a specified range but still has the
     * same reference angle (points in the same direction). The range must be at least
     * 360 degrees so that any reference angle can be represented within the range.
     * For example, [-180, 180] and [0, 360] are acceptable while [0, 90] is not.
     * Angles equal to the upper bound of the range will be wrapped to the lower bound.
     * @param minAngle the lower bound of the wrapping range
     * @param maxAngle the upper bound of the wrapping range
     * @return the wrapped angle
     */
    public Angle wrap(Angle minAngle, Angle maxAngle) {
        //Converting angles to degrees
        double min = minAngle.toDegrees();
        double max = maxAngle.toDegrees();

        //Making sure a valid range has been specified
        double diff = max - min;

        //If the range is smaller than 360 degrees
        if(diff < 360.0) {
            if(diff < 0) {
                //Range cannot be negative
                throw new MetalConfigurationException("Angle wrapping range is negative. " +
                        "Min angle must be less than max angle.");
            } else {
                //Range cannot be less than 360 degrees
                throw new MetalConfigurationException("Angle wrapping range is less than " +
                        "360 degrees. You must be able to represent any reference angle within the range.");
            }
        }

        double degrees = this.degrees; //Copying angle value into separate variable

        //Begin the wrapping
        //Make sure degree value is above the minimum
        while(degrees < min) {
            degrees += 360.0;
        }

        //Make sure degree value is below the maximum
        while(degrees >= max) {
            degrees -= 360.0;
        }

        //Return the wrapped angle
        return new Angle(degrees);
    }

    /**
     * This method wraps this angle between 0 (inclusive) and 360 (not inclusive).
     * @return the wrapped angle between 0 and 360
     */
    public Angle wrap() {
        return wrap(new Angle(0), new Angle(360));
    }

    /**
     * This method wraps this angle between -180 (inclusive) and 180 (not inclusive).
     * These bounds are commonly used when dealing with headings as it allows the use of
     * negative angles to represent counter-clockwise rotations.
     * @return the wrapped angle between -180 and 180
     */
    public Angle wrapNavigation() {
        return wrap(new Angle(-180), new Angle(180));
    }

    @Override
    public boolean equals(Object o) {
        //If object is not an angle, return false
        if(!(o instanceof Angle)) return false;
        //Now it's safe to cast o as an angle
        Angle a = (Angle) o;

        //Return true if they're similar within a tolerance
        return Math.abs(a.toDegrees() - degrees) < .0001;
    }
}
