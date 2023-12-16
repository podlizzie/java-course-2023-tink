package edu.hw11.task3;

import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public class ByteBuddyFibGenerator {
    public static final String CLASS_NAME = "edu.hw11.FibonacciCalculator";
    public static final String METHOD_NAME = "getFibonacciNumber";

    private ByteBuddyFibGenerator() {

    }

    public static Class<?> getFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(METHOD_NAME, long.class, Modifier.STATIC)
            .withParameters(int.class)
            .intercept(CustomFibonacci.getInstance())
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();
    }
}
