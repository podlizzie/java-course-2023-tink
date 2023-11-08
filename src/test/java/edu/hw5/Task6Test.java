package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @ParameterizedTest
    @ValueSource(strings = {
        "abc",
        "chf",
        "bg"
    })
    public void testThatStringIsSubsequenceOfAllStr(String subsequence) {
        String allStr = "achfdbaabgabcaabg";
        assertThat(Task6.isSubsequence(allStr, subsequence)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "xyz",
        "def",
        "acb"
    })
    public void testThatStringIsNotSubsequenceOfAllStr(String notSubsequence) {
        String allStr = "achfdbaabgabcaabg";
        assertThat(Task6.isSubsequence(allStr, notSubsequence)).isFalse();
    }
}
