package edu.hw6.task6test;

import edu.hw6.task6.PortScanner;
import edu.hw6.task6.ServiceLookupTable;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PortScannerTest {
    private final ServiceLookupTable serviceLookupTable = new ServiceLookupTable();
    private final PortScanner portScanner = new PortScanner(serviceLookupTable);

    @Test
    void testThatExceptionIsThrownForEndPortIsLessThanStartPort() {
        assertThatThrownBy(() -> portScanner.getOccupiedPortsInfo(500, 400)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid start or end port");
    }

    @Test
    void testThatExceptionIsThrownForStartOrEndPortNegative() {
        assertThatThrownBy(() -> portScanner.getOccupiedPortsInfo(-1, -20)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid start or end port");
    }

    @Test
    void testThatExceptionIsThrownForEndPortThatBiggerThenMaxPort() {
        assertThatThrownBy(() -> portScanner.getOccupiedPortsInfo(1, 500000)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid start or end port");
    }

    @Test
    void testThatMultipleServicesAreReturnedForMultipleOccupiedPorts() {
        List<String> expectedPortInfoList = Arrays.asList(
            "Port: 135 Protocol: TCP Service: EPMAP",
            "Port: 137 Protocol: UDP Service: Служба имен NetBIOS",
            "Port: 138 Protocol: UDP Service: Служба датаграмм NetBIOS"
        );

        List<String> portInfoList = portScanner.getOccupiedPortsInfo(134, 138);

        assertThat(portInfoList).containsExactlyInAnyOrderElementsOf(expectedPortInfoList);
    }

    @Test
    void testThatNoPortsAreReturnedForNoOccupiedPorts() {
        List<String> portInfoList = portScanner.getOccupiedPortsInfo(80, 81);
        assertThat(portInfoList).isEmpty();
    }
}
