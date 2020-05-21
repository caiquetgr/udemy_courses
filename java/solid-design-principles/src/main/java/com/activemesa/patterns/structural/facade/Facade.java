package com.activemesa.patterns.structural.facade;

import java.util.ArrayList;
import java.util.List;

// this class is a Facade (by simplifyng the creation of a Console/Viewport/Buffer
public class Facade {
    public static void main(String[] args) {
        Console console = newConsole(30, 20);
        console.render();
    }

    public static Console newConsole(int width, int height) {

        Buffer buffer = new Buffer(30, 20);
        Viewport viewport = new Viewport(buffer, 30, 20, 0, 0);
        Console console = new Console(30, 20);

        console.addViewPort(viewport);

        return console;
    }

}

class Buffer {

    private char[] characters;
    private int lineWidth;

    public Buffer(int lineWidth, int lineHeight) {
        this.lineWidth = lineWidth;
        this.characters = new char[lineWidth * lineHeight];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }

}

class Viewport {
    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offSetX;
    private final int offSetY;

    public Viewport(Buffer buffer, int width, int height, int offSetX, int offSetY) {

        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x + offSetX, y + offSetY);
    }
}

class Console {
    private List<Viewport> viewports = new ArrayList<>();
    int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addViewPort(Viewport viewport) {
        viewports.add(viewport);
    }

    public void render() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                for (Viewport vp : viewports) {
                    System.out.print(vp.charAt(x, y));
                }
            }
            System.out.println();
        }
    }
}
