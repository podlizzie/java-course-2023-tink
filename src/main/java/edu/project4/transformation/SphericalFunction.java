package edu.project4.transformation;

import edu.project4.entity.Point;

public class SphericalFunction implements Transformation {

    @Override
    public Point apply(Point oldPoint) {
        double x = oldPoint.x();
        double y = oldPoint.y();

        return new Point(x / (x * x + y * y), y / (x * x + y * y));
    }
}
