package vsu.cs;

import vsu.cs.transformations.Transformation;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import java.util.ArrayList;
import java.util.List;

public class CompositeTransformation implements Transformation {
    private final List<Transformation> transformations;
    private Matrix4d cachedMatrix;
    private boolean isDirty;


    public CompositeTransformation() {
        this.transformations = new ArrayList<>();
        this.cachedMatrix = new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
        this.isDirty = false;
    }

    public void add(Transformation transformation) {
        this.transformations.add(transformation);
        this.isDirty = true;
    }

    private void updateCachedMatrix() {
        Matrix4d result = new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );


        for (Transformation transformation : transformations) {
            Matrix4d temp = new Matrix4d();
            temp.mul(transformation.getMatrix(), result);
            result.set(temp);
        }
        this.cachedMatrix.set(result);
        this.isDirty = false;
    }
    @Override
    public Matrix4d getMatrix() {
        if (isDirty) {
            updateCachedMatrix();
        }
        return new Matrix4d(cachedMatrix);
    }

    @Override
    public Point3d apply(Point3d point) {
        Point3d result = new Point3d();
        this.getMatrix().transform(point, result);
        return result;
    }
}
