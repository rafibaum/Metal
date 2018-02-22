package com.rafibaum.metal.geometry;

import com.rafibaum.metal.utils.MetalConfigurationException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AngleTests {

    private static final double TOLERANCE = 0.001;

    /*
    Makes sure that angles can be initialized and read as either radians or degrees.
     */
    @Test
    public void angleConversions() {
        Angle r1 = new Angle(AngleUnit.RADIANS, Math.PI);
        Angle r2 = new Angle(AngleUnit.RADIANS, 1);
        Angle r3 = new Angle(AngleUnit.RADIANS, -7.86); //-7.86
        Angle d1 = new Angle(AngleUnit.DEGREES, 180);
        Angle d2 = new Angle(73.5);
        Angle d3 = new Angle(-3535.1);

        //Check degree conversions
        assertTrue(aboutEqual(180, r1.toDegrees()));
        assertTrue(aboutEqual(57.2958, r2.toDegrees()));
        assertTrue(aboutEqual(-450.3448, r3.toDegrees()));
        assertTrue(aboutEqual(180, d1.toDegrees()));
        assertTrue(aboutEqual(73.5, d2.toDegrees()));
        assertTrue(aboutEqual(-3535.1, d3.toDegrees()));

        //Check radian conversions
        assertTrue(aboutEqual(Math.PI, r1.toRadians()));
        assertTrue(aboutEqual(1, r2.toRadians()));
        assertTrue(aboutEqual(-7.86, r3.toRadians()));
        assertTrue(aboutEqual(Math.PI, d1.toRadians()));
        assertTrue(aboutEqual(1.282817, d2.toRadians()));
        assertTrue(aboutEqual(-61.6991344, d3.toRadians()));
    }

    /*
    Makes sure that basic math works with angles
     */
    @Test
    public void angleMaths() {
        Angle d1 = new Angle(45);
        Angle d2 = new Angle(-93.5);

        //Addition
        assertTrue(aboutEqual(45 + -93.5, d1.add(d2).toDegrees()));
        //Subtraction
        assertTrue(aboutEqual(45 - (-93.5), d1.subtract(d2).toDegrees()));
        //Multiplication
        assertTrue(aboutEqual(90, d1.multiply(2).toDegrees()));
        //Division
        assertTrue(aboutEqual(-93.5/2.5, d2.divide(2.5).toDegrees()));
    }

    /*
    Makes sure that wrapping works as described
     */
    @Test
    public void wrappingTest() {
        //Should not wrap
        double sameAngle = new Angle(187).wrap().toDegrees();
        assertTrue(aboutEqual(187, sameAngle));

        //Should wrap to 17 degrees
        double smallerAngle = new Angle(377).wrap().toDegrees();
        assertTrue(aboutEqual(17.0, smallerAngle));

        //Should wrap to -30
        double navAngle = new Angle(330).wrapNavigation().toDegrees();
        assertTrue(aboutEqual(-30, navAngle));
    }

    @Test
    public void equalsTest() {
        Angle a = new Angle(180);
        Angle b = new Angle(AngleUnit.RADIANS, Math.PI);
        Angle c = new Angle(540);

        //Should be equal
        assertTrue(a.equals(b));

        //Should not be
        assertFalse(a.equals(c));
    }

    /*
    Makes sure wrapping exception is thrown
     */
    @Test(expected = MetalConfigurationException.class)
    public void wrappingExceptionTest() {
        new Angle(160).wrap(new Angle(0), new Angle(1));
    }

    public boolean aboutEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < TOLERANCE;
    }

}
