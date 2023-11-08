package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    public void testThatFindFridaysOf13ReturnedCorrectResultWithInput1925() {
        List<String> result = Task2.findFridaysOf13("1925");
        assertThat(result).isEqualTo(List.of("1925-02-13", "1925-03-13", "1925-11-13"));
    }

    @Test
    public void testThatFindFridaysOf13ReturnedCorrectResultWithInput2024() {
        List<String> result = Task2.findFridaysOf13("2024");
        assertThat(result).isEqualTo(List.of("2024-09-13", "2024-12-13"));
    }

    @Test
    public void testThatFindNextFridayOf13ReturnedCorrectResult() {
        LocalDate date = LocalDate.of(1925, 2, 1);
        LocalDate result = Task2.findNextFridayOf13(date);
        assertThat(result).isEqualTo(LocalDate.of(1925, 2, 13));
    }
}
