package com.lanxiang.designpattern.decorator;

/**
 * Created by lanjing on 2017/2/19.
 */
public class DecoratorPattern {

    static interface Shape {
        void draw();
    }

    static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Shape: Rectangle");
        }
    }

    static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Shape: Circle");
        }
    }

    static class ShapeDecorator implements Shape {

        protected Shape decoratedShape;

        public ShapeDecorator(Shape decoratedShape){
            this.decoratedShape = decoratedShape;
        }

        @Override
        public void draw() {
            decoratedShape.draw();
        }
    }

    static class RedShapeDecorator extends ShapeDecorator{

        public RedShapeDecorator(Shape decoratedShape) {
            super(decoratedShape);
        }

        private void setRedBorder(Shape decoratedShape){
            System.out.println("Border Color: Red");
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }
    }

    public static void main(String[] args) {

        Shape circle = new Circle();

        Shape redCirclr = new RedShapeDecorator(new Circle());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        circle.draw();

        redCirclr.draw();

        redRectangle.draw();
    }
}
