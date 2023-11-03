package edu.hw3.task5;

import java.util.Comparator;
import org.jetbrains.annotations.NotNull;

public class PersonComparator implements Comparator<Person> {
    private final String order;

    PersonComparator(String order) {
        this.order = order;
    }

    @Override
    public int compare(@NotNull Person person1, @NotNull Person person2) {
        String lastName1 = getLastName(person1);
        String lastName2 = getLastName(person2);

        return order.equals("ASC") ? lastName1.compareTo(lastName2) : lastName2.compareTo(lastName1);
    }

    private String getLastName(@NotNull Person person) {
        return person.lastName != null ? person.lastName : person.firstName;
    }
}
