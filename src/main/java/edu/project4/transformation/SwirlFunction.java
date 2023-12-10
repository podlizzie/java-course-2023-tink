package edu.project4.transformation;

import edu.project4.entity.Point;

public class SwirlFunction implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double r = x * x + y * y;

        return new Point(
            x * Math.sin(r) - y * Math.cos(r),
            x * Math.cos(r) + y * Math.sin(r)
        );
    }
}
