package edu.hw2.task3.connection;

import edu.hw2.task3.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final double PROBABILITY = 0.5;
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        if (Math.random() < PROBABILITY) {
            throw new ConnectionException("Failed to execute command on faulty connection");
        }
        LOGGER.info("Executing command on faulty connection: " + command);
    }

    @Override
    public void close() {
        LOGGER.info("Closing faulty connection");
    }
}
