package com.rafibaum.metal.geometry;

/**
 * XY is a representation of a vector using cartesian (X and Y) coordinates.
 */
public class XY implements Vector {

    private double x;
    private double y;

    /**
     * Instantiates a cartesian vector with coordinates X and Y.
     * @param x X coordinate of the vector
     * @param y Y coordinate of the vector
     */
    public XY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X coordinate of the vector.
     * @return the X coordinate of the vector
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Returns the Y coordinate of the vector.
     * @return the Y coordinate of the vector
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Returns the magnitude of the vector.
     * @return the magnitude of the vector
     */
    @Override
    public double getMagnitude() {
        return Math.sqrt(x*x + y*y);
    }

    /**
     * Returns the angle of the vector wrapped between zero and 360 degrees,
     * where 360 degrees wraps to zero. Metal vectors use "navigation" style
     * angles where zero degrees represents forwards, and angles increase
     * clockwise from there.
     * @return the angle of the vector, wrapped between zero and 360 degrees (360 wraps to zero)
     */
    @Override
    public double getAngleDegrees() {
        return ((Math.atan2(x, y) + Math.PI) / Math.PI) * 180.0;
    }

    /**
     * Returns the angle of the vector wrapped between zero and 2π radians,
     * where 2π radians wraps to zero. Metal vectors use "navigation" style
     * angles where zero radians represents forwards, and angles increase
     * clockwise from there.
     * @return the angle of the vector, wrapped between zero and 2π radians (2π wraps to zero)
     */
    @Override
    public double getAngleRadians() {
        return Math.atan2(x, y) + Math.PI;
    }
}
