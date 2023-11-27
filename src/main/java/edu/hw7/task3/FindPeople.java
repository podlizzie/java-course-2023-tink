package edu.hw7.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class FindPeople implements PersonDatabase {
    private static final String ERROR_MSG = "Person data not complete.";
    private final Map<Integer, Person> database = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public synchronized void add(Person person) {
        lock.writeLock().lock();
        try {
            if (validatePersonAttributes(person)) {
                database.put(person.id(), person);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public synchronized void delete(int id) {
        lock.writeLock().lock();
        try {
            database.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return database.values().stream()
                .filter(person -> person.name().equals(name))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return database.values().stream()
                .filter(person -> person.address().equals(address))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return database.values().stream()
                .filter(person -> person.phoneNumber().equals(phone))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    private Boolean validatePersonAttributes(@NotNull Person person) throws InvalidPersonDataException {
        if (person.name() == null || person.phoneNumber() == null || person.address() == null) {
            throw new InvalidPersonDataException(ERROR_MSG);
        }
        return true;
    }
}
