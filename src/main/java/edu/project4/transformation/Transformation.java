package edu.project4.transformation;

import edu.project4.entity.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    Point apply(Point oldPoint);
}
