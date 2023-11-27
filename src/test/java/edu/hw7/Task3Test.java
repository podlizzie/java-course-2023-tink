package edu.hw7;

import edu.hw7.task3.FindPeople;
import edu.hw7.task3.InvalidPersonDataException;
import edu.hw7.task3.Person;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task3Test {

    FindPeople findPeople = new FindPeople();
    private static final Person person = new Person(1, "Liza B", "Lenina, 1", "89223345633");
    private static final Person person2 = new Person(2, "Masha s uralmasha", "Prospect, 2", "89223355633");
    private static final Person person3 = new Person(3, null, "Prospect, 2", "544451234");

    @Test
    void testThatAddPersonAddsPersonToDatabase() {
        // Given
        // When
        findPeople.add(person);

        // Then
        assertThat(findPeople.findByName("Liza B")).contains(person);
    }

    @Test
    void testThatDeletePersonRemovesPersonFromDatabase() {
        // Given
        findPeople.add(person);
        findPeople.add(person2);

        // When
        findPeople.delete(1);

        // Then
        assertThat(findPeople.findByName("Liza B")).isEmpty();
    }

    @Test
    void testThatFindByNameAddressPhoneReturnsCorrectPerson() {
        // Given
        findPeople.add(person);
        findPeople.add(person2);

        // When
        List<Person> result = findPeople.findByName("Masha s uralmasha");
        List<Person> result2 = findPeople.findByPhone("89223355633");
        List<Person> result3 = findPeople.findByAddress("Prospect, 2");

        // Then
        assertThat(result).contains(person2);
        assertThat(result2).contains(person2);
        assertThat(result3).contains(person2);
    }

    @Test
    void testThatADDReturnsExceptionWhenYouAddIncompleteData() {
        assertThatThrownBy(() -> findPeople.add(person3))
            .isInstanceOf(InvalidPersonDataException.class)
            .hasMessage("Person data not complete.");
    }

    @Test
    void testThatFindByNamePhoneAddressReturnsEmptyListWhenAddressNamePhoneNotInBase() {
        // Given
        findPeople.add(person);

        // When
        List<Person> result = findPeople.findByAddress("Samoletnaya");
        List<Person> result2 = findPeople.findByName("Mark");
        List<Person> result3 = findPeople.findByPhone("8922334445");

        // Then
        assertThat(result).isEmpty();
        assertThat(result2).isEmpty();
        assertThat(result3).isEmpty();
    }
}
