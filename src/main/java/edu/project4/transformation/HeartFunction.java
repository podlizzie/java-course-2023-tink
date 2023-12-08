package edu.project4.transformation;

import edu.project4.entity.Point;

public class HeartFunction implements Transformation{
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double sqrt = Math.sqrt(x * x + y * y);
        double argument = sqrt * Math.atan(y / x);

        return new Point(sqrt * Math.sin(argument), -sqrt * Math.cos(argument));
    }
}
