package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Test that iterator returns elements in reverse order")
    public void testThatIteratorReturnsElementsInReverseOrder() {
        Task8<String> iterator = new Task8<>(List.of("A", "B", "C", "D"));
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
        Task8<String> iterator = new Task8<>(List.of());
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    @DisplayName("Test that iterator returns elements in reverse order for single element collection")
    public void testThatIteratorReturnsElementsInReverseOrderForSingleElementCollection() {
        Task8<Integer> iterator = new Task8<>(List.of(1));

        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
    }
}
