package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class PortScanner {
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    private final ServiceLookupTable serviceLookupTable;

    public PortScanner(ServiceLookupTable serviceLookupTable) {
        this.serviceLookupTable = serviceLookupTable;
    }

    /**
     * Retrieves information about the occupied ports within the specified port range.
     *
     * @param startPort The starting port of the range to scan.
     * @param endPort   The ending port of the range to scan.
     * @return A list of strings, each containing information about an occupied port in the format
     *     "Port: [Port] Protocol: [Protocol] Service: [Service]".
     * @throws IllegalArgumentException if the provided start or end port is out of the valid range
     *                                  or if the start port is greater than the end port.
     */
    @SuppressWarnings("MultipleStringLiterals")
    public List<String> getOccupiedPortsInfo(int startPort, int endPort) {
        if (startPort < MIN_PORT || endPort < MIN_PORT || endPort > MAX_PORT || startPort > MAX_PORT
            || startPort > endPort) {
            throw new IllegalArgumentException("Invalid start or end port");
        }

        List<String> occupiedPortsInfo = new ArrayList<>();

        for (int port = startPort; port <= endPort; port++) {
            try (ServerSocket ignored = new ServerSocket(port)) {
                // Port is available
            } catch (Exception e) {
                occupiedPortsInfo.add("Port: " + port
                    + " Protocol: TCP Service: " + serviceLookupTable.getServiceName(port));
            }

            try (DatagramSocket ignored = new DatagramSocket(port)) {
                // Port is available
            } catch (Exception e) {
                occupiedPortsInfo.add("Port: " + port
                    + " Protocol: UDP Service: " + serviceLookupTable.getServiceName(port));
            }
        }

        return occupiedPortsInfo;
    }
}
