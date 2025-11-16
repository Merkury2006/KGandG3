package vsu.cs.transformations;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;

public interface Transformation {
    Matrix4d getMatrix();
    Point3d apply (Point3d point);
}
