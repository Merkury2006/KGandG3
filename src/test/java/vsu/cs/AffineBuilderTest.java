package vsu.cs;

import org.junit.jupiter.api.Test;
import vsu.cs.transformations.ScaleTransformation;
import vsu.cs.transformations.TranslationTransformation;

import javax.vecmath.Point3d;

import static junit.framework.Assert.*;

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
    void testVerySmallAngles() {
        Point3d point = new Point3d(1, 0, 0);
        Point3d result = new AffineBuilder()
                .rotateX(0.001)
                .rotateY(0.001)
                .rotateZ(0.001)
                .transform(point);

        assertTrue(Math.abs(result.x - 1) < 0.01);
        assertTrue(Math.abs(result.y) < 0.01);
        assertTrue(Math.abs(result.z) < 0.01);
    }

    @Test
    void testLargeAngles() {
        Point3d point = new Point3d(1, 0, 0);
        Point3d result = new AffineBuilder()
                .rotateX(Math.PI * 3)  // 540°
                .rotateY(Math.PI * 2)  // 360°
                .transform(point);

        assertFalse(Double.isNaN(result.x));
        assertFalse(Double.isNaN(result.y));
        assertFalse(Double.isNaN(result.z));
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
    void testComplexTransformation() {
        Point3d point = new Point3d(1, 2, 3);

        Point3d result = new AffineBuilder()
                .translate(5, 10, 15)
                .rotateX(Math.PI / 4)
                .rotateY(Math.PI / 3)
                .scale(2, 0.5, 3)
                .translate(-1, -2, -3)
                .transform(point);

        assertFalse(Double.isNaN(result.x));
        assertFalse(Double.isNaN(result.y));
        assertFalse(Double.isNaN(result.z));
    }

    @Test
    void testMultipleScaleOperations() {
        Point3d point = new Point3d(2, 3, 4);

        Point3d result = new AffineBuilder()
                .scaleX(2)
                .scaleY(3)
                .scaleZ(4)
                .scaleUniform(0.5)
                .transform(point);

        assertEquals(2, result.x, EPSILON);  // 2 * 2 * 0.5 = 2
        assertEquals(4.5, result.y, EPSILON); // 3 * 3 * 0.5 = 4.5
        assertEquals(8, result.z, EPSILON);   // 4 * 4 * 0.5 = 8
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

    @Test
    void testConsistency() {
        CompositeTransformation composite = new CompositeTransformation();
        composite.add(new TranslationTransformation(10, 0, 0));
        composite.add(new ScaleTransformation(2, 2, 2));

        Point3d point = new Point3d(1, 1, 1);

        Point3d viaApply = composite.apply(point);
        Point3d viaMatrix = new Point3d();
        composite.getMatrix().transform(point, viaMatrix);

        assertEquals(viaApply.x, viaMatrix.x, EPSILON);
        assertEquals(viaApply.y, viaMatrix.y, EPSILON);
        assertEquals(viaApply.z, viaMatrix.z, EPSILON);
    }

    @Test
    void testEmptyComposite() {
        CompositeTransformation composite = new CompositeTransformation();
        Point3d point = new Point3d(1, 2, 3);
        Point3d result = composite.apply(point);

        assertEquals(1, result.x, EPSILON);
        assertEquals(2, result.y, EPSILON);
        assertEquals(3, result.z, EPSILON);
    }

}
