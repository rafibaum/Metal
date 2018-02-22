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
     * Returns the angle of the vector wrapped between zero and 360 degrees.
     * @see Angle
     * @return the angle of the vector wrapped between zero and 360 degrees.
     */
    @Override
    public Angle getAngle() {
        return new Angle(AngleUnit.RADIANS, Math.atan2(x, y)).wrap();
    }

    /**
     * Adds a vector to this one.
     * @param vector the vector to be added to this one
     * @return the vector sum
     */
    @Override
    public Vector add(Vector vector) {
        return new XY(this.x + vector.getX(), this.y + vector.getY());
    }

    /**
     * Subtracts a vector from this one (as this - parameter = result).
     *
     * @param vector the vector to be subtracted from this one
     * @return the vector difference
     */
    @Override
    public Vector subtract(Vector vector) {
        return new XY(this.x - vector.getX(), this.y - vector.getY());
    }

    /**
     * Returns a vector which has been scaled by the scalar.
     *
     * @param scalar the number to scale the vector by
     * @return the scaled vector
     */
    @Override
    public Vector scale(double scalar) {
        return new XY(this.x * scalar, this.y * scalar);
    }

    /**
     * Returns a unit vector in the same direction as this one.
     *
     * @return a unit vector in the same direction as this one
     */
    @Override
    public Vector normalize() {
        //Normalizing a vector can be done by dividing the components of the vector by the overall magnitude of the vector.
        return new XY(this.x / this.getMagnitude(), this.y / getMagnitude());
    }

    /**
     * Returns the dot product of this vector and another one.
     *
     * @param vector the vector to dot with this one
     * @return the dot product of this vector and another one
     */
    @Override
    public double dotProduct(Vector vector) {
        //Dot product = x1 * x2 + y1 * y2
        return (this.x * vector.getX() + this.y * vector.getY());
    }

    /**
     * Returns the magnitude of the cross product of this vector and another one.
     *
     * @param vector the vector to cross with this one
     * @return the magnitude of the cross product of this vector and another one
     */
    @Override
    public double crossProduct(Vector vector) {
        //Obtains the angles of the two vectors, wrapped between 0 and 360
        Angle a1 = this.getAngle().wrap(new Angle(0), new Angle(360));
        Angle a2 = vector.getAngle().wrap(new Angle(0), new Angle(360));

        //Finds the angle between the two vectors
        double angleBetween = Math.abs(a1.toRadians() - a2.toRadians());

        //Magnitude of the cross product = |V1| * |V2| * sin(angle between vectors)
        return this.getMagnitude() * vector.getMagnitude() * Math.sin(angleBetween);
    }
}
