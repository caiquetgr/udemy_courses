package com.activemesa.patterns.creational.prototype;

class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point deepCopy() {
        return new Point(x, y);
    }
}

class Line {
    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line deepCopy() {
        return new Line(start.deepCopy(), end.deepCopy());
    }
}

public class PrototypeDemo {

    public static void main(String[] args) {
    }

}
