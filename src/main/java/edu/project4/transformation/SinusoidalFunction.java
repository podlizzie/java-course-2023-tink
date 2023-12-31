package edu.project4.transformation;

import edu.project4.entity.Point;

public class SinusoidalFunction implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
