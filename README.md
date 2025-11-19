# 🎯 Affine Transformations 3D

Библиотека для аффинных преобразований в 3D-пространстве. Реализует масштабирование, поворот 
и перенос с поддержкой матриц и кватернионов.

## 🚀 Быстрый старт

~~~java
import vsu.cs.AffineBuilder;
import vsu.cs.transformations.Transformation;

import javax.vecmath.Point3d;
//Способ 1: непосредственное применение к точке
Point3d point = new Point3d(1, 1, 1);
Point3d result = new AffineBuilder()
        .translate(10, 5, 0)
        .rotateZ(Math.PI / 4)
        .scale(2, 1, 1).build().apply(point);

//Способ 2: создание трансформации для многократного использования
Point3d point = new Point3d(1, 1, 1);
Transformation result = new AffineBuilder()
        .translate(10, 5, 0)
        .rotateZ(Math.PI / 4)
        .scale(2, 1, 1).build();
Point3d pointRes = result.apply(point);
~~~
## Преобразования
### Масштабирование
~~~java 
.scale(2, 2, 2)        // по всем осям
.scaleX(3)             // только по X
.scaleY(2)             // только по Y  
.scaleZ(1.5)           // только по Z
.scaleUniform(2)// равномерный масштаб
.scale(Axis.X, 5) //С заданием оси через Enum
~~~
### Поворот

~~~java
.rotateX(Math.PI / 2)  // матричный поворот вокруг X
.rotateY(Math.PI / 4)  // матричный поворот вокруг Y
.rotateZ(Math.PI / 6)  // матричный поворот вокруг Z
.rotate(Axis.X, Math.PI / 2) //Матричный поворот задание оси через Enum

.rotateXQuat(Math.PI / 2)  // кватернионный поворот вокруг X
.rotateYQuat(Math.PI / 4)  // кватернионный поворот вокруг Y
.rotateZQuat(Math.PI / 6)  // кватернионный поворот вокруг Z
.rotateQuat(Axis.X, Math.PI / 2) //кватернионный поворот задание оси через Enum
~~~
### Перенос
~~~java 
.translate(10, 5, 0)   // по всем осям
.translateX(10)        // только по X
.translateY(5)         // только по Y
.translateZ(3)         // только по Z
.translate(Axis.X, 5) //перенос с заданием оси через Enum
~~~

### Комбинирование преобразований
~~~java
Transformation transformation = new AffineBuilder()
    .translate(5, 0, 0)
    .rotateY(Math.PI / 4)
    .scale(2, 1, 1)
    .rotateZ(Math.PI / 6)
    .translate(0, 3, 0)
    .transform(point);

Point3d result = transformation.apply(new Point3d(x, y, z));
Matrix4d resultMatrix = transformation.getMatrix();
~~~

### Сохранение и восстановление состояния билдера
~~~java
AffineBuilder builder = new AffineBuilder()
    .translate(5, 0, 0)
    .rotateY(Math.PI / 4);

// Сохраняем текущее состояние
SaveTransformation saved = builder.saveState();

// Продолжаем преобразования
builder.scale(2, 1, 1).rotateZ(Math.PI / 6);

// Восстанавливаем сохраненное состояние
builder.restoreState(saved);
// Теперь builder содержит только: translate(5,0,0) + rotateY(π/4)
~~~