package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Test that iterator returns elements in reverse order")
    public void testThatIteratorReturnsElementsInReverseOrder() {
        BackwardIterator<String> iterator = new BackwardIterator<>(List.of("A", "B", "C", "D"));
        while (iterator.hasNext()) {
            assertThat(iterator.next()).isEqualTo("D");
            assertThat(iterator.next()).isEqualTo("C");
            assertThat(iterator.next()).isEqualTo("B");
            assertThat(iterator.next()).isEqualTo("A");
        }
    }

    @Test
    @DisplayName("Test that iterator returns elements in reverse order for empty collection")
    public void testThatIteratorReturnsElementsInReverseOrderForEmptyCollection() {
        BackwardIterator<String> iterator = new BackwardIterator<>(List.of());
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    @DisplayName("Test that iterator returns elements in reverse order for single element collection")
    public void testThatIteratorReturnsElementsInReverseOrderForSingleElementCollection() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of(1));

        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
    }
}
