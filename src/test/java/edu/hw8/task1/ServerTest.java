package edu.hw8.task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ServerTest {

    private static final String QUOTES_NOT_FOUND = "Цитата не найдена.";

    @Test
    void testThatCorrectQuoteIsReturnedForProvidedKeyword() {
        // given
        String keyword = "интеллект";
        String expectedQuote = "Чем ниже интеллект, тем громче оскорбления.";

        // when
        String actualQuote = Server.getQuoteForKeyword(keyword);

        // then
        assertThat(actualQuote).isEqualTo(expectedQuote);
    }

    @Test
    void testThatQuoteNotFoundMessageIsReturnedForUnknownKeyword() {
        // given
        String unknownKeyword = "fpfpfpffp";

        // when
        String response = Server.getQuoteForKeyword(unknownKeyword);

        // then
        assertThat(response).isEqualTo(QUOTES_NOT_FOUND);
    }
}
