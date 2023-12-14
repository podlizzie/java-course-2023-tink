package edu.hw10.task2;

import edu.hw10.task2.annotation.Cache;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CacheProxy implements InvocationHandler {
    private final Object object;
    private final Map<String, Object> cache = new HashMap<>();
    private final Path dir;

    public CacheProxy(Object object, Path dir) {
        this.object = object;
        this.dir = dir;
    }

    public static <T> T create(T obj, Class<?> objectClass, Path persistDirectory) {
        return (T) Proxy.newProxyInstance(
            objectClass.getClassLoader(),
            objectClass.getInterfaces(),
            new CacheProxy(obj, persistDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        String key = method.getName() + Arrays.toString(args);

        if (cacheAnnotation != null) {
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                Object result = method.invoke(object, args);
                cache.put(key, result);

                if (cacheAnnotation.persist()) {
                    persist(method, args, result);
                }

                return result;
            }
        }

        return method.invoke(object, args);
    }

    private void persist(Method method, Object[] args, Object result) throws IOException {
        String fileName = method.getName() + "_" + Arrays.toString(args) + ".cache";
        Path filePath = dir.resolve(fileName);

        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(filePath))) {
            out.writeObject(result);
        }
    }
}
