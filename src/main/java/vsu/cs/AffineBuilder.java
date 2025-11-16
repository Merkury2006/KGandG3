package vsu.cs;

import vsu.cs.transformations.*;

import javax.vecmath.Point3d;

public class AffineBuilder implements AffineBuilderInterface {
    private final CompositeTransformation composite;

    public AffineBuilder() {
        this.composite = new CompositeTransformation();
    }

    public AffineBuilder scale(double sx, double sy, double sz) {
        composite.add(new ScaleTransformation(sx, sy, sz));
        return this;
    }

    @Override
    public AffineBuilder scaleX(double scaleX) {
        composite.add(new ScaleTransformation(scaleX, 1, 1));
        return this;
    }

    @Override
    public AffineBuilder scaleY(double scaleY) {
        composite.add(new ScaleTransformation(1, scaleY, 1));
        return this;
    }

    @Override
    public AffineBuilder scaleZ(double scaleZ) {
        composite.add(new ScaleTransformation(1, 1, scaleZ));
        return this;
    }

    @Override
    public AffineBuilder scaleUniform(double uniformScale) {
        composite.add(new ScaleTransformation(uniformScale));
        return this;
    }

    @Override
    public AffineBuilder rotateX(double rotateX) {
        composite.add(new RotateTransformation(Axis.X, rotateX));
        return this;
    }

    @Override
    public AffineBuilder rotateXQuat(double rotateX) {
        composite.add(new RotateTransformationOnQuad(Axis.X, rotateX));
        return this;
    }

    @Override
    public AffineBuilder rotateY(double rotateY) {
        composite.add(new RotateTransformation(Axis.Y, rotateY));
        return this;
    }

    @Override
    public AffineBuilder rotateYQuat(double rotateY) {
        composite.add(new RotateTransformationOnQuad(Axis.Y, rotateY));
        return this;
    }

    @Override
    public AffineBuilder rotateZ(double rotateZ) {
        composite.add(new RotateTransformation(Axis.Z, rotateZ));
        return this;
    }

    @Override
    public AffineBuilder rotateZQuat(double rotateZ) {
        composite.add(new RotateTransformationOnQuad(Axis.Z, rotateZ));
        return this;
    }

    @Override
    public AffineBuilder translateX(double translateX) {
        composite.add(new TranslationTransformation(translateX, 0, 0));
        return this;
    }

    @Override
    public AffineBuilder translateY(double translateY) {
       composite.add(new TranslationTransformation(0, translateY, 0));
       return this;
    }

    @Override
    public AffineBuilder translateZ(double translateZ) {
        composite.add(new TranslationTransformation(0, 0, translateZ));
        return this;
    }

    @Override
    public AffineBuilder translate(double tx, double ty, double tz) {
        composite.add(new TranslationTransformation(tx, ty, tz));
        return this;
    }

    public CompositeTransformation build() {
        return composite;
    }

    public Point3d transform(Point3d point) {
        return composite.apply(point);
    }
}
