package edu.hw11;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public class CalculatorInterceptor {
    public CalculatorInterceptor() {

    }

    @RuntimeType
    public static int intercept(@Argument(0) int a, @Argument(1) int b) {
        return a * b;
    }
}
