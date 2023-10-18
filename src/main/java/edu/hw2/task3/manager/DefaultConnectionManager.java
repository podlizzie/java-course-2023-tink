package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private static final double PROBABILITY = 0.5;

    @Override
    public Connection getConnection() {
        if (Math.random() < PROBABILITY) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
