package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private static final String FIELD_NAME = "name";
    private static Student student;
    private static Method method;
    private static MethodHandle methodHandle;
    private Function<Student, String> lambdaMetaFactory;
    private static final String STUDENT_NAME = "Liza";
    private static final String STUDENT_SURNAME = "Bakulina";
    private static final String INTERFACE_METHOD_NAME = "apply";

    @Setup
    public void setup() throws Throwable {
        student = new Student(STUDENT_NAME, STUDENT_SURNAME);

        method = Student.class.getMethod(FIELD_NAME);

        methodHandle = getMethodHandle();

        lambdaMetaFactory = getLambdaFunction();
    }

    private MethodHandle getMethodHandle() throws Throwable {
        var lookup = MethodHandles.lookup();
        return lookup.unreflect(method);
    }

    private Function<Student, String> getLambdaFunction() throws Throwable {
        var lookup = MethodHandles.lookup();
        return (Function<Student, String>) LambdaMetafactory.metafactory(
                lookup,
                INTERFACE_METHOD_NAME,
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                methodHandle.type()
            )
            .getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        String name = student.name();
        blackhole.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole blackhole) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole blackhole) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void lambdaFunction(Blackhole blackhole) {
        String name = lambdaMetaFactory.apply(student);
        blackhole.consume(name);
    }
}
