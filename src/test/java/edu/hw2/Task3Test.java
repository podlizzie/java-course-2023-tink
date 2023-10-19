package edu.hw2;

import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.ConnectionManager;
import edu.hw2.task3.manager.DefaultConnectionManager;
import edu.hw2.task3.manager.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {

    @Test
    @DisplayName("Test default connection manager with probability = 1")
    public void testDefaultConnectionManager1() {
        ConnectionManager connectionManager = new DefaultConnectionManager(1.0);
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(connectionManager, 2);

        assertThatThrownBy(commandExecutor::updatePackages)
            .isInstanceOf(ConnectionException.class)
            .hasMessageContaining("Exceeded maximum number of attempts");

        assertThatThrownBy(() -> commandExecutor.tryExecute("apt update"))
            .isInstanceOf(ConnectionException.class)
            .hasMessageContaining("Exceeded maximum number of attempts");
    }

    @Test
    @DisplayName("Test faulty connection manager with probability = 1")
    public void testFaultyConnectionManager1() {
        ConnectionManager connectionManager = new FaultyConnectionManager(1.0);
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(connectionManager, 2);

        assertThatThrownBy(commandExecutor::updatePackages)
            .isInstanceOf(ConnectionException.class)
            .hasMessageContaining("Exceeded maximum number of attempts");

        assertThatThrownBy(() -> commandExecutor.tryExecute("apt update"))
            .isInstanceOf(ConnectionException.class)
            .hasMessageContaining("Exceeded maximum number of attempts");
    }

    @Test
    @DisplayName("Test default connection manager with probability = 0")
    public void testDefaultConnectionManager0() {
        ConnectionManager connectionManager = new DefaultConnectionManager(0.0);
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(connectionManager, 3);

        assertThatCode(commandExecutor::updatePackages)
            .doesNotThrowAnyException();

        assertThatCode(() -> commandExecutor.tryExecute("apt update"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Test faulty connection manager with probability = 0")
    public void testFaultyConnectionManager0() {
        ConnectionManager connectionManager = new FaultyConnectionManager(0.0);
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(connectionManager, 3);

        assertThatCode(commandExecutor::updatePackages)
            .doesNotThrowAnyException();

        assertThatCode(() -> commandExecutor.tryExecute("apt update"))
            .doesNotThrowAnyException();

    }
}
