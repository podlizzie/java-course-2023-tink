package edu.project4.transformation;

import edu.project4.Point;

public class DiskFunction implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double argument = Math.PI * Math.sqrt(x * x + y * y);
        double multiplier = Math.atan(y / x) / Math.PI;

        return new Point(multiplier * Math.sin(argument), multiplier * Math.cos(argument));
    }
}
