package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
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
        throws Exception {
        if (objectClass.isRecord()) {
            return createRecordInstance(objectClass);
        } else {
            T instance = createInstance(objectClass, fabricMethodName);
            populateFields(instance);
            return instance;
        }
    }

    private <T> T createRecordInstance(Class<T> recordClass) throws Exception {
        Class<?>[] fieldTypes = Arrays.stream(recordClass.getRecordComponents())
            .map(RecordComponent::getType)
            .toArray(Class<?>[]::new);
        Object[] parameters = Arrays.stream(recordClass.getDeclaredFields())
            .map(RandomObjectGenerator::getValue)
            .toArray();
        Constructor<T> constructor = recordClass.getDeclaredConstructor(fieldTypes);
        return constructor.newInstance(parameters);
    }

    private <T> T createInstance(Class<T> objectClass, String fabricMethodName)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = objectClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        if (fabricMethodName != null && !fabricMethodName.isEmpty()) {
            return (T) objectClass.getDeclaredMethod(fabricMethodName).invoke(null);
        } else {
            return constructor.newInstance();
        }
    }

    private <T> void populateFields(T instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotations() != null) {
                field.setAccessible(true);
                field.set(instance, getValue(field));
            }
        }
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
