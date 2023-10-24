package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    private final double probability;

    public FaultyConnectionManager(double probability) {
        this.probability = probability;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(probability);
    }
}
