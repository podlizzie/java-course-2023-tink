package edu.hw2.task4;

import java.util.ArrayList;
import java.util.List;

public class CreateCallingInfo {
    private CreateCallingInfo() {

    }

    public static List<CallingInfo> createCallingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        List<CallingInfo> callingInfoList = new ArrayList<>();

        for (int i = 2; i < stackTrace.length; i++) {
            StackTraceElement caller = stackTrace[i];
            callingInfoList.add(new CallingInfo(caller.getClassName(), caller.getMethodName()));
        }

        return callingInfoList;
    }
}
