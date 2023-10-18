package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final static Logger LOGGER = LogManager.getLogger();
    private String lastException = "";
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                lastException = "Attempt " + (attempts + 1) + " failed with ConnectionException. Retrying...";
                LOGGER.info(lastException);
            } catch (Exception e) {
                throw new RuntimeException("Failed to execute command", e);
            }
            attempts++;
        }
        throw new ConnectionException("Exceeded maximum number of attempts to execute command: " + command);
    }
}
