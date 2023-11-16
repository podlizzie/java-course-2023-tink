package edu.hw6.task6;

import java.util.HashMap;
import java.util.Map;

public class ServiceLookupTable {

    private final Map<Integer, String> serviceLookupTable;

    @SuppressWarnings("MagicNumber")
    public ServiceLookupTable() {
        serviceLookupTable = new HashMap<>();
        serviceLookupTable.put(135, "EPMAP");
        serviceLookupTable.put(137, "Служба имен NetBIOS");
        serviceLookupTable.put(138, "Служба датаграмм NetBIOS");
        serviceLookupTable.put(139, "Служба сеансов NetBIOS");
        serviceLookupTable.put(445, "Microsoft-DS Active Directory");
        serviceLookupTable.put(843, "Adobe Flash");
        serviceLookupTable.put(1900, "Simple Service Discovery Protocol (SSDP)");
        serviceLookupTable.put(3702, "Динамическое обнаружение веб-служб");
        serviceLookupTable.put(5353, "Многоадресный DNS");
        serviceLookupTable.put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        serviceLookupTable.put(17500, "Dropbox");
        serviceLookupTable.put(27017, "MongoDB");
    }

    public String getServiceName(int port) {
        String service = serviceLookupTable.get(port);
        return (service != null) ? service : "Unknown";
    }
}
