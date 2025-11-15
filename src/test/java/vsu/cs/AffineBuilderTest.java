package vsu.cs;

import org.junit.jupiter.api.Test;

import javax.vecmath.Point3d;

import static junit.framework.Assert.assertEquals;

public class AffineBuilderTest {
    private static final double EPSILON = 1E-10;

    @Test
    void testScaleX() {
        Point3d point = new Point3d(2, 3, 4);
        Point3d result = new AffineBuilder().scaleX(5).transform(point);
        assertEquals(10, result.x, EPSILON);
        assertEquals(3, result.y, EPSILON);
        assertEquals(4, result.z, EPSILON);
    }

    @Test
    void testScaleY() {
        Point3d point = new Point3d(2, 3, 4);
        Point3d result = new AffineBuilder().scaleY(5).transform(point);
        assertEquals(2, result.x, EPSILON);
        assertEquals(15, result.y, EPSILON);
        assertEquals(4, result.z, EPSILON);
    }

    @Test
    void testScaleZ() {
        Point3d point = new Point3d(2, 3, 4);
        Point3d result = new AffineBuilder().scaleZ(5).transform(point);
        assertEquals(2, result.x, EPSILON);
        assertEquals(3, result.y, EPSILON);
        assertEquals(20, result.z, EPSILON);
    }

    @Test
    void testScaleUniform() {
        Point3d point = new Point3d(1, 2, 3);
        Point3d result = new AffineBuilder().scaleUniform(5).transform(point);
        assertEquals(5, result.x, EPSILON);
        assertEquals(10, result.y, EPSILON);
        assertEquals(15,  result.z, EPSILON);
    }

    @Test
    void testScaleZero() {
        Point3d point = new Point3d(1, 1, 1);
        Point3d result = new AffineBuilder().scale(0, 0, 0).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testRotateOnX() {
        Point3d point = new Point3d(0, 1, 0);
        Point3d result = new AffineBuilder().rotateX(Math.PI / 2).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(-1, result.z, EPSILON);
    }

    @Test
    void testRotateOnXQuat() {
        Point3d point = new Point3d(0, 1, 0);
        Point3d result = new AffineBuilder().rotateXQuat(Math.PI / 2).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(-1, result.z, EPSILON);
    }

    @Test
    void testRotateOnY() {
        Point3d point = new Point3d(0, 0, 1);
        Point3d result = new AffineBuilder().rotateY(Math.PI / 2).transform(point);
        assertEquals(1, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testRotateOnYQuat() {
        Point3d point = new Point3d(0, 0, 1);
        Point3d result = new AffineBuilder().rotateYQuat(Math.PI / 2).transform(point);
        assertEquals(1, result.x, EPSILON);
        assertEquals(0, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testRotateOnZ() {
        Point3d point = new Point3d(1, 0, 0);
        Point3d result = new AffineBuilder().rotateZ(Math.PI / 2).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(-1, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testRotateOnZQuat() {
        Point3d point = new Point3d(1, 0, 0);
        Point3d result = new AffineBuilder().rotateZQuat(Math.PI / 2).transform(point);
        assertEquals(0, result.x, EPSILON);
        assertEquals(-1, result.y, EPSILON);
        assertEquals(0, result.z, EPSILON);
    }

    @Test
    void testScaleThenTranslate() {
        Point3d point = new Point3d(1, 1, 1);
        Point3d result = new AffineBuilder().scale(2, 2, 2).translate(10, 10, 10).transform(point);
        assertEquals(12, result.x, EPSILON);
        assertEquals(12, result.y, EPSILON);
        assertEquals(12, result.z, EPSILON);
    }

    @Test
    void testTranslateThenScale() {
        Point3d point = new Point3d(1, 1, 1);
        Point3d result = new AffineBuilder().translate(10, 10, 10).scale(2, 2, 2).transform(point);
        assertEquals(22, result.x, EPSILON);
        assertEquals(22, result.y, EPSILON);
        assertEquals(22, result.z, EPSILON);
    }

    @Test
    void testMatrixQuaternionEquivalence() {
        Point3d point = new Point3d(2, 3, 4);

        Point3d matrixResult = new AffineBuilder()
                .rotateX(Math.PI / 3)
                .rotateY(Math.PI / 4)
                .rotateZ(Math.PI / 6)
                .transform(point);

        Point3d quatResult = new AffineBuilder()
                .rotateXQuat(Math.PI / 3)
                .rotateYQuat(Math.PI / 4)
                .rotateZQuat(Math.PI / 6)
                .transform(point);

        assertEquals(matrixResult.x, quatResult.x, EPSILON);
        assertEquals(matrixResult.y, quatResult.y, EPSILON);
        assertEquals(matrixResult.z, quatResult.z, EPSILON);
    }

    @Test
    void testIdentity() {
        Point3d point = new Point3d(1, 2, 3);
        Point3d result = new AffineBuilder().transform(point);

        assertEquals(1, result.x, EPSILON);
        assertEquals(2, result.y, EPSILON);
        assertEquals(3, result.z, EPSILON);
    }

}
