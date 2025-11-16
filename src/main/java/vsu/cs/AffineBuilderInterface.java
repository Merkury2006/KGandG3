package vsu.cs;

import javax.vecmath.Point3d;

public interface AffineBuilderInterface {
    CompositeTransformation build();
    Point3d transform(Point3d point);


    AffineBuilder scaleX(double scaleX);
    AffineBuilder scaleY(double scaleY);
    AffineBuilder scaleZ(double scaleZ);
    AffineBuilder scaleUniform(double uniformScale);
    AffineBuilder scale(double scaleX, double scaleY, double scaleZ);
    AffineBuilder rotateX(double rotateX);
    AffineBuilder rotateXQuat(double rotateX);
    AffineBuilder rotateY(double rotateY);
    AffineBuilder rotateYQuat(double rotateY);
    AffineBuilder rotateZ(double rotateZ);
    AffineBuilder rotateZQuat(double rotateZ);
    AffineBuilder translateX(double translateX);
    AffineBuilder translateY(double translateY);
    AffineBuilder translateZ(double translateZ);
    AffineBuilder translate(double translateX, double translateY, double translateZ);
}
