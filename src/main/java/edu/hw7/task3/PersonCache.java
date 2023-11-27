package edu.hw7.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class PersonCache implements PersonDatabase {
    private final Map<Integer, Person> database = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            database.put(person.id(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            database.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return database.values().stream()
                .filter(person -> person.name().equals(name) && isAttributesNotNull(person))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return database.values().stream()
                .filter(person -> person.address().equals(address) && isAttributesNotNull(person))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return database.values().stream()
                .filter(person -> person.phoneNumber().equals(phone) && isAttributesNotNull(person))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    private boolean isAttributesNotNull(@NotNull Person person) {
        return person.name() != null
            && person.phoneNumber() != null
            && person.address() != null;
    }
}
