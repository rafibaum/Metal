package com.rafibaum.metal.geometry;

import org.junit.Test;

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

        //Check degree conversions
        assertTrue(aboutEqual(180, r1.toDegrees()));
        assertTrue(aboutEqual(57.2958, r2.toDegrees()));
        assertTrue(aboutEqual(-450.3448, r3.toDegrees()));

        //Check radian conversions
        assertTrue(aboutEqual(Math.PI, r1.toRadians()));
        assertTrue(aboutEqual(1, r2.toRadians()));
        assertTrue(aboutEqual(-7.86, r3.toRadians()));
    }


    public boolean aboutEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < TOLERANCE;
    }

}
