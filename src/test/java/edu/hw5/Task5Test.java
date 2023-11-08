package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @ParameterizedTest
    @ValueSource(strings = {
        "А123ВЕ777",
        "О777ОО177"
    })
    public void testThatCarNumberValidationReturnsTrue(String number) {
        assertThat(Task5.carNumberValidation(number)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777"
    })
    public void testThatCarNumberValidationReturnsFalse(String number) {
        assertThat(Task5.carNumberValidation(number)).isFalse();
    }
}
