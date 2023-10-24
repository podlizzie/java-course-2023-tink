package edu.hw2;

import edu.hw2.task4.CallingInfo;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.task4.CreateCallingInfo.createCallingInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Test not null check")
    public void testNotNullCheck() {
        List<CallingInfo> callingInfoList = createCallingInfo();
        assertThat(callingInfoList).isNotEmpty();
        assertThat(callingInfoList).hasSizeGreaterThan(0);
        assertThat(callingInfoList).extracting("className").isNotNull();
        assertThat(callingInfoList).extracting("methodName").isNotNull();
    }

    @Test
    @DisplayName("Test get calling info get 0")
    public void testGetCallingInfo() {
        List<CallingInfo> callingInfoList = createCallingInfo();
        assertThat(callingInfoList).isNotEmpty();
        assertThat(callingInfoList.get(0).className()).isEqualTo("edu.hw2.task4.CreateCallingInfo");
        assertThat(callingInfoList.get(0).methodName()).isEqualTo("createCallingInfo");
    }

    @Test
    @DisplayName("Test get calling info get 1")
    public void testGetCallingInfo2() {
        List<CallingInfo> callingInfoList = createCallingInfo();
        assertThat(callingInfoList).isNotEmpty();
        assertThat(callingInfoList.get(1).className()).isEqualTo("edu.hw2.Task4Test");
        assertThat(callingInfoList.get(1).methodName()).isEqualTo("testGetCallingInfo2");
    }

}
