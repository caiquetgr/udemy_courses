package com.activemesa.patterns.structural.decorator;

public class DynamicDecoratorComposition {

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

    static class ColoredShape implements Shape {

        private Shape shape;
        private String color;

        public ColoredShape(Shape shape, String color) {
            this.shape = shape;
            this.color = color;
        }

        @Override
        public String getInfo() {
            return shape.getInfo() + " has the color " + color;
        }

    }

    static class TransparentShape implements Shape {
        private Shape shape;
        private int transparency;

        public TransparentShape(Shape shape, int transparency) {
            this.shape = shape;
            this.transparency = transparency;
        }

        @Override
        public String getInfo() {
            return shape.getInfo() + " has " + transparency + "% transparency";
        }
    }

    public static void main(String[] args) {

        Circle circle = new Circle(10);
        System.out.println(circle.getInfo());

        ColoredShape blueSquare = new ColoredShape(new Square(20), "blue");
        System.out.println(blueSquare.getInfo());

        TransparentShape greenTransparentCircle = new TransparentShape(new ColoredShape(new Circle(5), "green"), 50);
        System.out.println(greenTransparentCircle.getInfo());

    }


}
