package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    public void testThatReturnedMultiplicationInsteadOfTheSum() throws InstantiationException, IllegalAccessException {
        int expected = 12;

        Class<?> dynamicType = new ByteBuddy()
            .subclass(Calculator.class)
            .method(ElementMatchers.named("getSum"))
            .intercept(MethodDelegation.to(CalculatorInterceptor.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        Calculator instance = (Calculator) dynamicType.newInstance();
        assertEquals(instance.getSum(3, 4), expected);
    }
}
