package edu.hw2.task4;

import java.util.ArrayList;
import java.util.List;

public class CreateCallingInfo {
    private CreateCallingInfo() {

    }

    public static List<CallingInfo> createCallingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        List<CallingInfo> callingInfoList = new ArrayList<>();

        for (StackTraceElement caller : stackTrace) {
            callingInfoList.add(new CallingInfo(caller.getClassName(), caller.getMethodName()));
        }

        return callingInfoList;
    }
}
