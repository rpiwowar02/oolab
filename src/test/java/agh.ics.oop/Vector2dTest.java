package agh.ics.oop;

import org.junit.jupiter.api.Assertions;

class Vector2dTest {

    @org.junit.jupiter.api.Test
    void toStringTest() {
        Vector2d tmp = new Vector2d(100,0);
        Assertions.assertEquals("(100,0)",tmp.toString());
    }

    @org.junit.jupiter.api.Test
    void precedes() {
        Vector2d v1 = new Vector2d(300,400);
        Vector2d v2 = new Vector2d(400,500);
        Vector2d v3 = new Vector2d(400,600);
        Assertions.assertTrue(v1.precedes(v2));
        Assertions.assertTrue(v1.precedes(v3));
        Assertions.assertTrue(v2.precedes(v3));
        Assertions.assertTrue(v1.precedes(v1));
        Assertions.assertTrue(v2.precedes(v2));
        Assertions.assertTrue(v3.precedes(v3));
        Assertions.assertFalse(v2.precedes(v1));
        Assertions.assertFalse(v3.precedes(v1));
        Assertions.assertFalse(v2.precedes(v1));

    }

    @org.junit.jupiter.api.Test
    void follows() {
        Vector2d v1 = new Vector2d(300,400);
        Vector2d v2 = new Vector2d(400,500);
        Vector2d v3 = new Vector2d(400,600);
        Assertions.assertTrue(v2.follows(v1));
        Assertions.assertTrue(v3.follows(v1));
        Assertions.assertTrue(v3.follows(v2));
        Assertions.assertTrue(v1.follows(v1));
        Assertions.assertTrue(v2.follows(v2));
        Assertions.assertTrue(v3.follows(v3));
        Assertions.assertFalse(v1.follows(v2));
        Assertions.assertFalse(v1.follows(v3));
        Assertions.assertFalse(v2.follows(v3));
    }

    @org.junit.jupiter.api.Test
    void add() {
        Vector2d A = new Vector2d(300,400);
        Vector2d B = new Vector2d(200,450);
        Vector2d C = new Vector2d(500,850);
        Assertions.assertEquals(C,A.add(B));
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        Vector2d A = new Vector2d(300,400);
        Vector2d B = new Vector2d(200,450);
        Vector2d C = new Vector2d(500,850);
        Assertions.assertEquals(A,C.subtract(B));
    }

    @org.junit.jupiter.api.Test
    void upperRight() {
        Vector2d A = new Vector2d(300,600);
        Vector2d B = new Vector2d(700,500);
        Vector2d expected = new Vector2d(700,600);
        Assertions.assertEquals(expected,A.upperRight(B));
    }

    @org.junit.jupiter.api.Test
    void lowerLeft() {
        Vector2d A = new Vector2d(300,600);
        Vector2d B = new Vector2d(700,500);
        Vector2d expected = new Vector2d(300,500);
        Assertions.assertEquals(expected,A.lowerLeft(B));
    }

    @org.junit.jupiter.api.Test
    void opposite() {
        Vector2d A = new Vector2d(300,600);
        Vector2d expected = new Vector2d(-300,-600);
        Assertions.assertEquals(expected,A.opposite());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Vector2d T1 = new Vector2d(0,0);
        Vector2d T2 = new Vector2d(0,0);
        Assertions.assertEquals(T1,T2);
        Vector2d T3 = new Vector2d(10,6);
        Vector2d T4 = new Vector2d(10,6);
        Assertions.assertEquals(T3,T4);
        Vector2d T5 = new Vector2d(20,12);
        Assertions.assertEquals(T5,T3.add(T4));
        Vector2d T6 = new Vector2d(1000,600);
        Vector2d T7 = new Vector2d(1000,600);
        Assertions.assertEquals(T6,T7);
        Vector2d T8 = new Vector2d(2000,1200);
        Assertions.assertEquals(T8,T6.add(T7));

    }
}