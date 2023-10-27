package edu.hw3;

import edu.hw3.task7.NullComparator;
import java.util.Comparator;
import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Test that contains null returns true")
    public void testThatContainsNullReturnsTrue() {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator<>(String.CASE_INSENSITIVE_ORDER));
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Test that contains null as a key returns true")
    public void testThatContainsNullAsAKeyReturnsTrue() {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator<>(Comparator.naturalOrder()));
        tree.put(null, "test");
        assertThat(tree.get(null)).isEqualTo("test");
    }
}
