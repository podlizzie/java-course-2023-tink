package edu.hw6.task5test;

import edu.hw6.task5.HackerNews;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTest {

    @Test
    @DisplayName("Test that hackerNewsTopStories method returns non-empty array")
    void testThatHackerNewsTopStoriesReturnedNonEmptyArray()
        throws IOException, InterruptedException, URISyntaxException {
        long[] topStories = HackerNews.hackerNewsTopStories();
        assertThat(topStories).isNotEmpty();
    }

    @Test
    @DisplayName("Test that news method returns a non-null title for a valid ID")
    void testThatNewsReturnedNonNullTitleForValidID()
        throws IOException, URISyntaxException, InterruptedException {
        long[] topStories = HackerNews.hackerNewsTopStories();
        if (topStories.length > 0) {
            String title = HackerNews.news(topStories[0]);
            assertThat(title).isNotNull();
        }
    }

    @Test
    @DisplayName("Test that news method returns correct title for ID 37570037")
    void testThatNewsReturnedCorrectTitle()
        throws IOException, URISyntaxException, InterruptedException {
        String title = HackerNews.news(37570037);

        String expectedTitle = "JDK 21 Release Notes";

        assertThat(title).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("Test that news method returns null for an invalid ID")
    void testThatNewsReturnedNullForInvalidID() throws IOException, URISyntaxException, InterruptedException {
        String title = HackerNews.news(-1);
        assertThat(title).isNull();
    }
}
