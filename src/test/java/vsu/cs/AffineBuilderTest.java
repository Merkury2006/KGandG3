package vsu.cs;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.vecmath.Point3d;

import java.awt.*;

import static junit.framework.Assert.assertEquals;

public class AffineBuilderTest {
    private AffineBuilder affineBuilder;
    private static final double EPSILON = 1E-10;

    @BeforeEach
    void setUp() {
        affineBuilder = new AffineBuilder();
    }


    @Test
    void testScaleX() {
        Point3d point = new Point3d(2, 3, 4);
        Point3d result = affineBuilder.scaleX(5).transform(point);
        assertEquals(10, result.x, EPSILON);
        assertEquals(3, result.y, EPSILON);
        assertEquals(4, result.z, EPSILON);
    }

    @Test
    void testScaleY() {
        Point3d point = new Point3d(2, 3, 4);
        Point3d result = affineBuilder.scaleY(5).transform(point);
        assertEquals(2, result.x, EPSILON);
        assertEquals(15, result.y, EPSILON);
        assertEquals(4, result.z, EPSILON);
    }

    @Test
    void testScaleZ() {
        Point3d point = new Point3d(2, 3, 4);
        Point3d result = affineBuilder.scaleZ(5).transform(point);
        assertEquals(2, result.x, EPSILON);
        assertEquals(3, result.y, EPSILON);
        assertEquals(20, result.z, EPSILON);
    }

    @Test
    void testScaleUniform() {
        Point3d point = new Point3d(1, 2, 3);
        Point3d result = affineBuilder.scaleUniform(5).transform(point);
        assertEquals(5, result.x, EPSILON);
        assertEquals(10, result.y, EPSILON);
        assertEquals(15,  result.z, EPSILON);
    }

    @Test
    void testScaleZero() {
        Point3d point = new Point3d(1, 1, 1);
        Point3d result = affineBuilder.scale(0, 0, 0).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testRotateOnX() {
        Point3d point = new Point3d(0, 1, 0);
        Point3d result = affineBuilder.rotateX(Math.PI / 2).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(-1, result.z, EPSILON);
    }

    @Test
    void testRotateOnY() {
        Point3d point = new Point3d(0, 0, 1);
        Point3d result = affineBuilder.rotateY(Math.PI / 2).transform(point);
        assertEquals(1, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testRotateOnZ() {
        Point3d point = new Point3d(1, 0, 0);
        Point3d result = affineBuilder.rotateZ(Math.PI / 2).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(-1, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testScaleThenTranslate() {
        Point3d point = new Point3d(1, 1, 1);
        Point3d result = affineBuilder.scale(2, 2, 2).translate(10, 10, 10).transform(point);
        assertEquals(12, result.x, EPSILON);
        assertEquals(12, result.y, EPSILON);
        assertEquals(12, result.z, EPSILON);
    }

    @Test
    void testTranslateThenScale() {
        Point3d point = new Point3d(1, 1, 1);
        Point3d result = affineBuilder.translate(10, 10, 10).scale(2, 2, 2).transform(point);
        assertEquals(22, result.x, EPSILON);
        assertEquals(22, result.y, EPSILON);
        assertEquals(22, result.z, EPSILON);

    }

    @Test
    void testIdentity() {
        Point3d point = new Point3d(1, 2, 3);
        Point3d result = affineBuilder.transform(point);

        assertEquals(1, result.x, EPSILON);
        assertEquals(2, result.y, EPSILON);
        assertEquals(3, result.z, EPSILON);
    }

}
