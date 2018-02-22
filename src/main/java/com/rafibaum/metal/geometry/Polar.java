package com.rafibaum.metal.geometry;

/**
 * Polar is a representation of vectors using polar coordinates. By default Metal will not wrap
 * the magnitude or angle to any regular range. Negative magnitudes and angles outside of [0, 360]
 * will be maintained unless you call wrapMagnitude or wrapAngle, or wrap.
 */
public class Polar implements Vector {

    private double magnitude;
    private Angle angle;

    /**
     * Instantiates a polar vector given a vector magnitude and an angle.
     * @param magnitude the magnitude of the vector
     * @param angle the angle of the vector
     */
    public Polar(double magnitude, Angle angle) {
        this.magnitude = magnitude;
        this.angle = angle;
    }

    /**
     * Instantiates a polar vector given a vector magnitude and an angle in degrees.
     * @param magnitude the magnitude of the vector
     * @param degrees the angle of the vector in degrees
     */
    public Polar(double magnitude, double degrees) {
        this(magnitude, new Angle(AngleUnit.DEGREES, degrees));
    }

    /**
     * Returns the X coordinate of the vector.
     * @return the X coordinate of the vector
     */
    @Override
    public double getX() {
        return magnitude * Math.sin(angle.toRadians());
    }

    /**
     * Returns the Y coordinate of the vector.
     * @return the Y coordinate of the vector
     */
    @Override
    public double getY() {
        return magnitude * Math.cos(angle.toRadians());
    }

    /**
     * Returns the magnitude of the vector.
     * @return the magnitude of the vector
     */
    @Override
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * Returns the angle of the vector as instantiated.
     * @return the angle of the vector
     */
    @Override
    public Angle getAngle() {
        return angle;
    }

    /**
     * Adds a vector to this one.
     *
     * @param vector the vector to be added to this one
     * @return the vector sum
     */
    @Override
    public Vector add(Vector vector) {
        return new XY(this.getX() + vector.getX(), this.getY() + vector.getY());
    }

    /**
     * Subtracts a vector from this one (as this - parameter = result).
     *
     * @param vector the vector to be subtracted from this one
     * @return the vector difference
     */
    @Override
    public Vector subtract(Vector vector) {
        return new XY(this.getX() - vector.getX(), this.getY() - vector.getY());
    }

    /**
     * Returns a vector which has been scaled by the scalar.
     *
     * @param scalar the number to scale the vector by
     * @return the scaled vector
     */
    @Override
    public Vector scale(double scalar) {
        return new Polar(this.magnitude * scalar, this.angle);
    }

    /**
     * Returns a unit vector in the same direction as this one.
     *
     * @return a unit vector in the same direction as this one
     */
    @Override
    public Vector normalize() {
        return new Polar(1.0, this.angle);
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
        return (this.getX() * vector.getX() + this.getY() * vector.getY());
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

    /**
     * This method makes polar vector "regular" by performing two operations. First,
     * it makes sure that if the magnitude of the polar vector is negative that the sign
     * is made positive and the angle is adjusted accordingly. Then, it wraps the vector's
     * angle to be between zero and 360 degrees.
     * @return a vector pointing in the same direction with positive magnitude and a valid reference angle
     */
    public Polar wrap() {
        double magnitude = this.magnitude;
        Angle angle = this.angle;

        //If magnitude is negative, flip sign and angle
        if(magnitude < 0) {
            magnitude = this.magnitude * -1; //Flip magnitude sign
            angle = angle.add(new Angle(180));
        }


        angle = angle.wrap(); //Wrap angles to within [0, 360] range after rotating angle

        return new Polar(magnitude, angle);
    }

    /**
     * This method returns an equivalent polar vector with an angle wrapped between zero
     * and 360 degrees.
     * @return an equivalent vector with an angle wrapped between zero and 360 degrees.
     */
    public Polar wrapAngle() {
        Angle angle = this.angle.wrap(); //Should wrap between zero and 360

        return new Polar(magnitude, angle);
    }

    @Override
    public boolean equals(Object o) {
        //If object is not a vector, it cannot be equal
        if(!(o instanceof Vector)) return false;

        //Now it's safe to cast the object as a vector
        Vector v = (Vector) o;

        //If x and y coordinates are equal within tolerance, the vectors are equal
        if(Math.abs(v.getX() - this.getX()) < .0001 &&
                Math.abs(v.getY() - this.getY()) < .0001) {
            return true;
        }

        return false;
    }
}
