package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @ParameterizedTest
    @ValueSource(strings = {"password~", "password!", "password@", "password#", "password$", "password%", "password^",
        "password&", "password*", "password|"})
    public void testThatPasswordWithRequiredCharactersReturnsTrue(String password) {
        assertThat(Task4.passwordValidation(password)).isTrue();
    }

    @Test
    public void testThatPasswordWithoutRequiredCharactersReturnsFalse() {
        String password = "password";
        assertThat(Task4.passwordValidation(password)).isFalse();
    }
}
