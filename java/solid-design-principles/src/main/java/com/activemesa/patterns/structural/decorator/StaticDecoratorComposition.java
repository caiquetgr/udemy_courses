package com.activemesa.patterns.structural.decorator;

import java.util.function.Supplier;

public class StaticDecoratorComposition {

    interface Shape {
        String getInfo();
    }

    static class Circle implements Shape {

        private float radius;

        public Circle() {
        }

        public Circle(float radius) {
            this.radius = radius;
        }

        void resize(float factor) {
            radius *= factor;
        }

        @Override
        public String getInfo() {
            return "A circle of radius " + radius;
        }
    }

    static class Square implements Shape {

        private float side;

        public Square() {
        }

        public Square(float side) {
            this.side = side;
        }

        @Override
        public String getInfo() {
            return "A square of side " + side;
        }
    }

    static class ColoredShape<T extends Shape> implements Shape {

        private Shape shape;
        private String color;

        public ColoredShape(Supplier<? extends T> constructor,
                            String color) {
            this.shape = constructor.get();
            this.color = color;
        }

        @Override
        public String getInfo() {
            return shape.getInfo() + " has the color " + color;
        }
    }

    static class TransparentShape<T extends Shape> implements Shape {

        private Shape shape;
        private int transparency;

        public TransparentShape(Supplier<? extends T> constructor,
                                int transparency) {
            this.shape = constructor.get();
            this.transparency = transparency;
        }

        @Override
        public String getInfo() {
            return shape.getInfo() + " has " + transparency + "% transparency";
        }

    }

    public static void main(String[] args) {

        ColoredShape<Square> coloredSquare = new ColoredShape<>(() -> new Square(50), "blue");
        System.out.println(coloredSquare.getInfo());

        TransparentShape<ColoredShape<Circle>> transparentColoredCircle = new TransparentShape<>(
                () -> new ColoredShape<>(() -> new Circle(50), "red"),
                50
        );

        System.out.println(transparentColoredCircle.getInfo());

    }


}
