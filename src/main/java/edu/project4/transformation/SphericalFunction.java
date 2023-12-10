package edu.project4.transformation;

import edu.project4.entity.Point;

public class SphericalFunction implements Transformation {

    @Override
    public Point apply(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        double newX = x / (x * x + y * y);
        double newY = y / (x * x + y * y);

        return new Point(newX, newY);
    }
}
