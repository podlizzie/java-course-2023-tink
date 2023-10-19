package edu.hw2.task2;

public class Square extends Shape {
    private double sideLength;

    public void setHeight(double height) {
        this.sideLength = height;
    }

    public void setWeight(double weight) {
        this.sideLength = weight;
    }

    @Override public double getWidth() {
        return sideLength;
    }

    @Override public double getHeight() {
        return sideLength;
    }

    @Override public double area() {
        return sideLength * sideLength;
    }

}
