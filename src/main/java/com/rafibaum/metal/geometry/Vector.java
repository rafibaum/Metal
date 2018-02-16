package com.rafibaum.metal.geometry;

/**
 * This interface describes the basic functionality that vector classes should
 * have in Metal and how they should function. Vector classes which implement this interface
 * should be immutable.
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
     * Returns the angle of the vector.
     * @see Angle
     * @return the angle of the vector
     */
    Angle getAngle();

    /**
     * Adds a vector to this one.
     * @param vector the vector to be added to this one
     * @return the vector sum
     */
    Vector add(Vector vector);

    /**
     * Subtracts a vector from this one (as this - parameter = result).
     * @param vector the vector to be subtracted from this one
     * @return the vector difference
     */
    Vector subtract(Vector vector);

    /**
     * Returns a vector which has been scaled by the scalar.
     * @param scalar the number to scale the vector by
     * @return the scaled vector
     */
    Vector scale(double scalar);

    /**
     * Returns a unit vector in the same direction as this one.
     * @return a unit vector in the same direction as this one
     */
    Vector normalize();

    /**
     * Returns the dot product of this vector and another one.
     * @param vector the vector to dot with this one
     * @return the dot product of this vector and another one
     */
    double dotProduct(Vector vector);

    /**
     * Returns the magnitude of the cross product of this vector and another one.
     * @param vector the vector to cross with this one
     * @return the magnitude of the cross product of this vector and another one
     */
    double crossProduct(Vector vector);

}
