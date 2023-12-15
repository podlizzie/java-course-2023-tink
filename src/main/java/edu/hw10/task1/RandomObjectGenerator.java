package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Random;

public class RandomObjectGenerator {

    private static final Random RANDOM = new Random();

    public <T> T nextObject(Class<T> objectClass) throws Exception {
        return nextObject(objectClass, null);
    }

    public <T> T nextObject(Class<T> objectClass, String fabricMethodName)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T instance;

        if (objectClass.isRecord()) {
            Class<?>[] fieldTypes =
                Arrays.stream(objectClass.getRecordComponents())
                    .map(RecordComponent::getType)
                    .toArray(Class<?>[]::new);

            Object[] parameters = Arrays.stream(objectClass.getDeclaredFields())
                .map(RandomObjectGenerator::getValue)
                .toArray();

            Constructor<T> constructor = objectClass.getDeclaredConstructor(fieldTypes);
            constructor.setAccessible(true);
            instance = constructor.newInstance(parameters);
        } else {
            Constructor<T> constructor = objectClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            if (fabricMethodName != null && !fabricMethodName.isEmpty()) {
                instance = (T) objectClass.getDeclaredMethod(fabricMethodName).invoke(null);
            } else {
                instance = constructor.newInstance();
            }

            for (Field field : objectClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Min.class) || field.isAnnotationPresent(Max.class)
                    || field.isAnnotationPresent(NotNull.class)) {
                    field.setAccessible(true);
                    field.set(instance, getValue(field));
                }
            }
        }

        return instance;
    }

    private static Object getValue(Field field) {
        Class<?> type = field.getType();
        Min minAnnotation = field.getAnnotation(Min.class);
        Max maxAnnotation = field.getAnnotation(Max.class);

        if (type.equals(int.class) || type.equals(Integer.class)) {
            int minValue = (minAnnotation != null) ? minAnnotation.minVal() : Integer.MIN_VALUE;
            int maxValue = (maxAnnotation != null) ? maxAnnotation.maxVal() : Integer.MAX_VALUE;

            if (minValue >= maxValue) {
                throw new IllegalArgumentException(String.format(
                    "Min value %d must be less than max value %d for field %s",
                    minValue, maxValue, field.getName()
                ));
            }

            return RANDOM.nextInt(minValue, maxValue);
        } else if (type.equals(String.class)) {
            if (field.getAnnotation(NotNull.class) != null) {
                return "non-null string";
            }
            return null;
        }
        return null;
    }
}
