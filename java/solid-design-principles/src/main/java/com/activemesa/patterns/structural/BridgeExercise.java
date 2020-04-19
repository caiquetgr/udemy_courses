package com.activemesa.patterns.structural;

public class BridgeExercise {

    abstract class Shape {

        protected final Renderer renderer;

        public Shape(Renderer renderer) {
            this.renderer = renderer;
        }

        public abstract String getName();
    }

    interface Renderer {
        public String whatToRenderAs();
    }

    class Triangle extends Shape {
        public Triangle(Renderer renderer) {
            super(renderer);
        }

        @Override
        public String getName() {
            return "Triangle";
        }

        @Override
        public String toString() {
            return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
        }
    }

    class Square extends Shape {
        public Square(Renderer renderer) {
            super(renderer);
        }

        @Override
        public String getName() {
            return "Square";
        }

        @Override
        public String toString() {
            return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
        }
    }

    class VectorSquare extends Square {
        public VectorSquare(Renderer renderer) {
            super(renderer);
        }

        @Override
        public String toString() {
            return String.format("Drawing %s as lines", getName());
        }
    }

    class RasterSquare extends Square {
        public RasterSquare(Renderer renderer) {
            super(renderer);
        }

        @Override
        public String toString() {
            return String.format("Drawing %s as pixels", getName());
        }
    }

    class VectorRenderer implements Renderer {
        @Override
        public String whatToRenderAs() {
            return "lines";
        }
    }

    class RasterRenderer implements Renderer {
        @Override
        public String whatToRenderAs() {
            return "pixels";
        }
    }


// imagine VectorTriangle and RasterTriangle are here too

}
