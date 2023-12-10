package edu.project4.transformation;

import edu.project4.entity.Point;

public class RombFunction implements Transformation {
    @Override
    public Point apply(Point point) {
        var x = point.x();
        var y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        return new Point(
            Math.sin(theta) * Math.cos(r),
            Math.cos(theta) * Math.sin(r)
        );
    }
}
