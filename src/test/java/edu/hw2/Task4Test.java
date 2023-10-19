package edu.hw2;

import edu.hw2.task4.CallingInfo;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.task4.CreateCallingInfo.createCallingInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Test get calling info")
    public void testGetCallingInfo() {
        List<CallingInfo> callingInfoList = createCallingInfo();
        assertThat(callingInfoList).isNotEmpty();
        assertThat(callingInfoList.get(0).className()).isEqualTo("edu.hw2.Task4Test");
        assertThat(callingInfoList.get(0).methodName()).isEqualTo("testGetCallingInfo");
    }

    @Test
    @DisplayName("Test should contain all calling methods")
    public void TestShouldContainAllCallingMethods() {
        List<CallingInfo> callingInfoList = createCallingInfo();
        assertThat(callingInfoList.size()).isGreaterThan(2);
    }

}
