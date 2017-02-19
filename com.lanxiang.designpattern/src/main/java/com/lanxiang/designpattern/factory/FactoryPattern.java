package com.lanxiang.designpattern.factory;

/**
 * Created by lanjing on 2017/2/19.
 */
public class FactoryPattern {

    static interface Shape {
        void draw();
    }

    static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Rectangle : draw() method.");
        }
    }

    static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Circle : draw() method.");
        }
    }

    static class ShapeFactory {

        public Shape getShape(String shapeType) {
            if (shapeType == null) {
                return null;
            }
            if (shapeType.equalsIgnoreCase("Rectangle")) {
                return new Rectangle();
            } else if (shapeType.equalsIgnoreCase("Circle")) {
                return new Circle();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape circle = factory.getShape("circle");
        circle.draw();
        Shape rectangle = factory.getShape("rectangle");
        circle.draw();
    }
}
