package edu.hw3;

import edu.hw3.task5.ParseContacts;
import edu.hw3.task5.Person;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Contacts are sorted by last name in ascending order")
    public void testThatContactsAreSortedByLastNameInASC() {
        String[] names = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String order = "ASC";
        List<Person> sortedPeople = ParseContacts.parseContacts(names, order);

        String[] expectedNames = {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};

        for (int i = 0; i < sortedPeople.size(); i++) {
            String fullName = sortedPeople.get(i).getFullName();
            assertThat(fullName).isEqualTo(expectedNames[i]);
        }
    }

    @Test
    @DisplayName("Contacts are sorted by last name in descending order")
    public void testThatContactsAreSortedByLastNameInDESC() {
        String[] names = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String order = "DESC";
        List<Person> sortedPeople = ParseContacts.parseContacts(names, order);

        String[] expectedNames = {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};

        for (int i = 0; i < sortedPeople.size(); i++) {
            String fullName = sortedPeople.get(i).getFullName();
            assertThat(fullName).isEqualTo(expectedNames[i]);
        }
    }

    @Test
    @DisplayName("Contacts are sorted by first name when last name is missing in ascending order")
    public void testThatContactsAreSortedByFirstNameWhenLastNameIsMissingInASC() {
        String[] names = {"Queen", "Thomas Aquinas", "Anna", "Rene Descartes"};
        String order = "ASC";
        List<Person> sortedPeople = ParseContacts.parseContacts(names, order);

        String[] expectedNames = {"Anna", "Thomas Aquinas", "Rene Descartes", "Queen"};

        for (int i = 0; i < sortedPeople.size(); i++) {
            String fullName = sortedPeople.get(i).getFullName();
            assertThat(fullName).isEqualTo(expectedNames[i]);
        }
    }

    @Test
    @DisplayName("Empty array returns empty list")
    public void testThatEmptyArrayReturnsEmptyList() {
        String[] names = {};
        String order = "DESC";
        List<Person> sortedContacts = ParseContacts.parseContacts(names, order);

        List<Person> expected = new ArrayList<>();

        assertThat(sortedContacts).isEqualTo(expected);
    }

    @Test
    @DisplayName("Null array returns empty list")
    public void testThatNullArrayReturnsEmptyList() {
        String[] names = null;
        String order = "DESC";
        List<Person> sortedContacts = ParseContacts.parseContacts(names, order);

        List<Person> expected = new ArrayList<>();

        assertThat(sortedContacts).isEqualTo(expected);
    }
}
