package edu.hw2.task2;

public class Square extends Shape {
    private double sideLength;

    void setHeight(double height) {
        this.sideLength = height;
    }

    void setWeight(double weight) {
        this.sideLength = weight;
    }

    @Override
    double getWidth() {
        return sideLength;
    }

    @Override
    double getHeight() {
        return sideLength;
    }

    @Override
    double area() {
        return sideLength * sideLength;
    }

}
