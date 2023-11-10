package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    public void testThatDateStringIsParsedAsISOFormat() {
        String date = "2020-10-10";
        LocalDate expected = LocalDate.of(2020, 10, 10);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatDateStringIsParsedWithImplicitDay() {
        String date = "2020-12-2";
        LocalDate expected = LocalDate.of(2020, 12, 2);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatDateStringIsParsedAsCustomFormat() {
        String date = "12/2/2020";
        LocalDate expected = LocalDate.of(2020, 12, 2);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatDateStringIsParsedWithExplicitYear() {
        String date = "1/3/1976";
        LocalDate expected = LocalDate.of(1976, 1, 3);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatDateStringIsParsedWithImplicitYear() {
        String date = "1/3/20";
        LocalDate expected = LocalDate.of(2020, 1, 3);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatTomorrowReturnsNextDay() {
        String date = "tomorrow";
        LocalDate expected = LocalDate.now().plusDays(1);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatTodayReturnsCurrentDate() {
        String date = "today";
        LocalDate expected = LocalDate.now();

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatYesterdayReturnsPreviousDay() {
        String date = "yesterday";
        LocalDate expected = LocalDate.now().minusDays(1);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatXDaysAgoReturnsCorrectDate() {
        String date = "1 day ago";
        LocalDate expected = LocalDate.now().minusDays(1);

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testThatInvalidStringReturnsEmptyOptional() {
        String date = "null";
        Optional<LocalDate> expected = Optional.empty();

        Optional<LocalDate> result = Task3.parseDate(date);

        assertThat(result).isEqualTo(expected);
    }
}
