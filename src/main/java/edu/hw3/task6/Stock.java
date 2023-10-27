package edu.hw3.task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final String name;
    private final int cost;

    public Stock(String name, int cost) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return Integer.compare(o.cost, this.cost);
    }
}
