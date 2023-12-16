package edu.hw11;

import edu.hw11.task3.ByteBuddyFibGenerator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void testThatCreatedClassWithMethodWorkCorrectly()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        long expected = 75025L;
        int input = 25;
        Class<?> FibonacciClass = ByteBuddyFibGenerator.getFibonacciClass();
        Method method = FibonacciClass.getDeclaredMethod(ByteBuddyFibGenerator.METHOD_NAME, int.class);

        //when
        long returnedNumber = (long) method.invoke(null, input);

        //then
        assertThat(returnedNumber).isEqualTo(expected);
    }
}
