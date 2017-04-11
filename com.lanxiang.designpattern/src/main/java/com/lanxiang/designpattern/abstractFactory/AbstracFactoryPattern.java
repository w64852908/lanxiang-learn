package com.lanxiang.designpattern.abstractFactory;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/5.
 */
public class AbstracFactoryPattern {

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

    static interface Color {
        void fill();
    }

    static class Red implements Color {
        @Override
        public void fill() {
            System.out.println("Inside Red::fill() method.");
        }
    }

    static class Green implements Color {

        @Override
        public void fill() {
            System.out.println("Inside Green::fill() method.");
        }
    }

    static abstract class AbstractFactory {
        abstract Shape getShape(String shape);

        abstract Color getColor(String color);
    }

    static class ShapeFactory extends AbstractFactory {

        @Override
        Shape getShape(String shape) {
            if (shape == null) {
                return null;
            }
            if (shape.equalsIgnoreCase("Rectangle")) {
                return new Rectangle();
            } else if (shape.equalsIgnoreCase("Circle")) {
                return new Circle();
            }
            return null;
        }

        @Override
        Color getColor(String color) {
            return null;
        }
    }

    static class ColorFactory extends AbstractFactory {

        @Override
        Shape getShape(String shape) {
            return null;
        }

        @Override
        Color getColor(String color) {
            if (color == null) {
                return null;
            }
            if (color.equalsIgnoreCase("RED")) {
                return new Red();
            } else if (color.equalsIgnoreCase("GREEN")) {
                return new Green();
            }
            return null;
        }
    }

    static class FactoryProducer {
        public static AbstractFactory getFactory(String choice) {
            if (choice.equalsIgnoreCase("SHAPE")) {
                return new ShapeFactory();
            } else if (choice.equalsIgnoreCase("COLOR")) {
                return new ColorFactory();
            }
            return null;
        }
    }

    @Test
    public void run() {
        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        //获取形状为 Circle 的对象
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //调用 Circle 的 draw 方法
        shape1.draw();

        //获取形状为 Rectangle 的对象
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        //获取颜色为 Red 的对象
        Color color1 = colorFactory.getColor("RED");

        //调用 Red 的 fill 方法
        color1.fill();

        //获取颜色为 Green 的对象
        Color color2 = colorFactory.getColor("Green");

        //调用 Green 的 fill 方法
        color2.fill();
    }
}
