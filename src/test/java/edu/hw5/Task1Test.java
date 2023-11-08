package edu.hw5;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {
    @Test
    void testThatCalculateSessionTimeReturnedCorrectDuration() {
        String[] input = {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };

        assertThat(Task1.calculateSessionTime(input))
            .isEqualTo(Duration.ofHours(3).plusMinutes(40));
    }

    @Test
    void testThatCalculateSessionTimeReturnedExceptionOfFormat() {
        String[] input = {
            "2022.03.12, 20:20 - 2022-03-12, 23.50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };

        assertThatThrownBy(() -> Task1.calculateSessionTime(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Illegal line");
    }

    @Test
    void testThatCalculateSessionTimeReturnedExceptionBecauseInputEmpty() {
        String[] input = {};

        assertThatThrownBy(() -> Task1.calculateSessionTime(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input array is empty");
    }
}
