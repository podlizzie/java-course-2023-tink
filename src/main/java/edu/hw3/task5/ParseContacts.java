package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ParseContacts {
    private ParseContacts() {

    }

    public static @NotNull List<Person> parseContacts(String[] names, String order) {
        List<Person> people = new ArrayList<>();

        if (names == null || names.length == 0) {
            return people;
        }

        for (String name : names) {
            String[] parts = name.split(" ");
            if (parts.length != 2) {
                people.add(new Person(parts[0], null));
            } else {
                people.add(new Person(parts[0], parts[1]));
            }
        }

        Comparator<Person> comparator = new PersonComparator(order);
        people.sort(comparator);

        return people;
    }
}
