package com.rafibaum.metal.geometry;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class VectorTest {

    private static final double TOLERANCE = 0.001;

    /*
    Testing conversions for XY vectors into cartesian and polar coordinates.
     */
    @Test
    public void XYconversions() {
        XY v1 = new XY(3, 4);
        XY v2 = new XY(0, -3);
        XY v3 = new XY(54, -64);

        //V1 tests
        assertTrue(aboutEqual(3, v1.getX()));
        assertTrue(aboutEqual(4, v1.getY()));
        assertTrue(aboutEqual(5, v1.getMagnitude()));
        assertTrue(aboutEqual(36.8699, v1.getAngle().toDegrees()));

        //V2 tests
        assertTrue(aboutEqual(0, v2.getX()));
        assertTrue(aboutEqual(-3, v2.getY()));
        assertTrue(aboutEqual(3, v2.getMagnitude()));
        assertTrue(aboutEqual(180, v2.getAngle().toDegrees()));

        //V3 tests
        assertTrue(aboutEqual(54, v3.getX()));
        assertTrue(aboutEqual(-64, v3.getY()));
        assertTrue(aboutEqual(83.737685, v3.getMagnitude()));
        assertTrue(aboutEqual(139.844, v3.getAngle().toDegrees()));
    }

    @Test
    public void XYmath() {
        XY a1 = new XY(34.32, 14.53);
        XY a2 = new XY(24.54, -124.632);

        //Addition
        Vector a3 = a1.add(a2);
        assertTrue(aboutEqual(34.32 + 24.54, a3.getX()));
        assertTrue(aboutEqual(14.53 - 124.632, a3.getY()));

        //Subtraction
        Vector sub = a2.subtract(a1);
        assertTrue(aboutEqual(24.54 - 34.32, sub.getX()));
        assertTrue(aboutEqual(-124.632 - 14.53, sub.getY()));

        //Scaling
        Vector scale = a1.scale(2.5);
        assertTrue(aboutEqual(34.32 * 2.5, scale.getX()));
        assertTrue(aboutEqual(14.53 * 2.5, scale.getY()));

        //Normalizing
        Vector norm = a2.normalize();
        double magnitude = Math.sqrt(a2.getX() * a2.getX() + a2.getY() * a2.getY());
        assertTrue(aboutEqual(a2.getX() / magnitude, norm.getX()));
        assertTrue(aboutEqual(a2.getY() / magnitude, norm.getY()));
    }

    /*
    Testing conversions for polar vetors into cartesian and polar coordinates.
     */
    @Test
    public void polarConversions() {
        Polar v1 = new Polar(5, 30);
        Polar v2 = new Polar(-3, 180);
        Polar v3 = new Polar(54, -64);

        //V1 tests
        assertTrue(aboutEqual(2.5, v1.getX()));
        assertTrue(aboutEqual(4.3301, v1.getY()));
        assertTrue(aboutEqual(5, v1.getMagnitude()));
        assertTrue(aboutEqual(30, v1.getAngle().toDegrees()));

        //V2 tests
        assertTrue(aboutEqual(0, v2.getX()));
        assertTrue(aboutEqual(3, v2.getY()));
        assertTrue(aboutEqual(-3, v2.getMagnitude()));
        assertTrue(aboutEqual(180, v2.getAngle().toDegrees()));

        //V3 tests
        assertTrue(aboutEqual(-48.5349, v3.getX()));
        assertTrue(aboutEqual(23.6720, v3.getY()));
        assertTrue(aboutEqual(54, v3.getMagnitude()));
        assertTrue(aboutEqual(-64, v3.getAngle().toDegrees()));
    }

    /*
    Tests Polar vector wrapping to make sure it works
     */
    @Test
    public void polarWrapping() {
        Polar v1 = new Polar(-3, 180).wrap();
        Polar v2 = new Polar(54, -64).wrap();
        Polar v3 = new Polar(54, -64).wrapAngle();

        //V1 tests
        assertTrue(aboutEqual(3, v1.getMagnitude()));
        assertTrue(aboutEqual(0, v1.getAngle().toDegrees()));

        //V2 tests
        assertTrue(aboutEqual(54, v2.getMagnitude()));
        assertTrue(aboutEqual(296, v2.getAngle().toDegrees()));

        //V3 tests
        assertTrue(aboutEqual(54, v3.getMagnitude()));
        assertTrue(aboutEqual(296, v3.getAngle().toDegrees()));
    }

    /*
    Testing angle conversions as these can be tricky
     */
    @Test
    public void angleTest() {
        XY q1 = new XY(1, 1);
        XY q2 = new XY(1, -1);
        XY q3 = new XY(-1, -1);
        XY q4 = new XY(-1, 1);

        assertTrue(aboutEqual(45, q1.getAngle().toDegrees()));
        assertTrue(aboutEqual(135, q2.getAngle().toDegrees()));
        assertTrue(aboutEqual(225, q3.getAngle().toDegrees()));
        assertTrue(aboutEqual(315, q4.getAngle().toDegrees()));
    }

    public boolean aboutEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < TOLERANCE;
    }

}
