package vsu.cs;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Point4d;

public class AffineBuilder implements AffineBuilderInterface {
    private Matrix4d matrix;

    public AffineBuilder() {
        this.matrix = new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    @Override
    public Matrix4d build() {
        return new Matrix4d(matrix);
    }

    @Override
    public Point3d transform(Point3d point) {
        Point4d point4d = new Point4d(point.getX(), point.getY(), point.getZ(), 1.0);
        Point4d result = new Point4d();
        this.matrix.transform(point4d, result);
        return new Point3d(result.getX()/result.getW(), result.getY()/result.getW(), result.getZ()/result.getW());
    }

    @Override
    public AffineBuilder scaleX(double scaleX) {
        Matrix4d matrixScale = new Matrix4d(
                scaleX, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixScale, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder scaleY(double scaleY) {
        Matrix4d matrixScale = new Matrix4d(
                1, 0, 0, 0,
                0, scaleY, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixScale, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder scaleZ(double scaleZ) {
        Matrix4d matrixScale = new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, scaleZ, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixScale, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder scaleUniform(double uniformScale) {
        Matrix4d matrixScale = new Matrix4d(
                uniformScale, 0, 0, 0,
                0, uniformScale, 0, 0,
                0, 0, uniformScale, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixScale, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder scale(double scaleX, double scaleY, double scaleZ) {
        Matrix4d matrixScale = new Matrix4d(
                scaleX, 0, 0, 0,
                0, scaleY, 0, 0,
                0, 0, scaleZ, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixScale, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder rotateX(double rotateX) {
        Matrix4d matrixRotate = new Matrix4d(
                1, 0, 0, 0,
                0, Math.cos(rotateX), Math.sin(rotateX), 0,
                0, -Math.sin(rotateX), Math.cos(rotateX), 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixRotate, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder rotateY(double rotateY) {
        Matrix4d matrixRotate = new Matrix4d(
                Math.cos(rotateY), 0, Math.sin(rotateY), 0,
                0, 1, 0, 0,
                -Math.sin(rotateY), 0, Math.cos(rotateY), 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixRotate, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder rotateZ(double rotateZ) {
        Matrix4d matrixRotate = new Matrix4d(
                Math.cos(rotateZ), Math.sin(rotateZ), 0, 0,
                -Math.sin(rotateZ), Math.cos(rotateZ), 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixRotate, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder translateX(double translateX) {
        Matrix4d matrixTranslate = new Matrix4d(
                1, 0, 0, translateX,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixTranslate, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder translateY(double translateY) {
        Matrix4d matrixTranslate = new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, translateY,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixTranslate, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder translateZ(double translateZ) {
        Matrix4d matrixTranslate = new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, translateZ,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixTranslate, this.matrix);
        return this;
    }

    @Override
    public AffineBuilder translate(double translateX, double translateY, double translateZ) {
        Matrix4d matrixTranslate = new Matrix4d(
                1, 0, 0, translateX,
                0, 1, 0, translateY,
                0, 0, 1, translateZ,
                0, 0, 0, 1
        );
        this.matrix.mul(matrixTranslate, this.matrix);
        return this;
    }
}
