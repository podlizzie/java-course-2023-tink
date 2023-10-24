package edu.hw2.task2;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override public double getWidth() {
        return width;
    }

    @Override public double getHeight() {
        return height;
    }

    @Override public double area() {
        return height * width;
    }

}
