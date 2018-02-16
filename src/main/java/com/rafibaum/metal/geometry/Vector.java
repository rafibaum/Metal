package com.rafibaum.metal.geometry;

/**
 * This interface describes the basic functionality that vector classes should
 * have in Metal and how they should function.
 */
public interface Vector {

    /**
     * Returns the X coordinate of the vector.
     * @return the X coordinate of the vector
     */
    double getX();

    /**
     * Returns the Y coordinate of the vector.
     * @return the Y coordinate of the vector
     */
    double getY();

    /**
     * Returns the magnitude of the vector.
     * @return the magnitude of the vector
     */
    double getMagnitude();

    /**
     * Returns the angle of the vector. Metal vectors use "navigation" style
     * angles where zero degrees represents forwards, and angles increase
     * clockwise from there.
     * @return the angle of the vector in degrees
     */
    double getAngleDegrees();

    /**
     * Returns the angle of the vector. Metal vectors use "navigation" style
     * angles where zero radians represents forwards, and angles increase
     * clockwise from there.
     * @return the angle of the vector in radians
     */
    double getAngleRadians();

}
