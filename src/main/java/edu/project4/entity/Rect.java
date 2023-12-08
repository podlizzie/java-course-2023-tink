package edu.project4.entity;

import org.jetbrains.annotations.NotNull;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(@NotNull Point p) {
        return x <= p.x() && p.x() < x + width && y <= p.y() && p.y() < y + height;
    }
}
