package edu.project2.model;

public final class Cell {
    private Type type;

    public Cell(int row, int col, Type type) {
        this.type = type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        WALL,
        PASSAGE
    }
}

