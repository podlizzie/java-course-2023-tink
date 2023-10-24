package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private final double probability;

    public DefaultConnectionManager(double probability) {
        this.probability = probability;
    }

    @Override
    public Connection getConnection() {
        if (Math.random() < probability) {
            return new FaultyConnection(probability);
        }
        return new StableConnection();
    }
}
