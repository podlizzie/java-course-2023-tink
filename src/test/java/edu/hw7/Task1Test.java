package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    public void testThatCounterReturnedInitialValueZero() {
        // given
        int initialValue = Task1.getCount();

        // when
        Task1.runIncrement();

        // then
        assertEquals(initialValue + 2, Task1.getCount());
    }

    @Test
    public void testThatMultipleThreadsIncrementCounterSafely() {
        // given
        int initialValue = Task1.getCount();

        // when
        for (int i = 0; i < 100; i++) {
            Task1.runIncrement();
        }

        // then
        assertEquals(initialValue + 200, Task1.getCount());
    }

}
