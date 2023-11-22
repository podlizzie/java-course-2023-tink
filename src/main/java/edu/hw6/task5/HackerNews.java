package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {

    private static final String COMMON_URL = "https://hacker-news.firebaseio.com/v0/";
    private static final String ITEM_URL = "item/%d.json";
    private static final String TOP_STORIES = "topstories.json";
    private static final String REGEX_TITLE = "\"title\" *: *\"([^\"]+)\"";
    private static final int TIMEOUT = 3;
    private static final int GOOD_RESPONSE_STATUS = 200;

    private HackerNews() {

    }

    /**
     * Sends an HTTP GET request to the specified URL and returns the response.
     *
     * @param url the URL to send the request to
     * @return the HTTP response as a HttpResponse object
     * @throws IOException          if an I/O error occurs while sending or receiving the HTTP request
     * @throws InterruptedException if the sending of the HTTP request is interrupted
     * @throws URISyntaxException   if a URI syntax error occurs
     */
    private static HttpResponse<String> sendHttpRequest(String url)
        throws IOException, InterruptedException, URISyntaxException {
        var request = HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET()
            .timeout(Duration.of(TIMEOUT, ChronoUnit.SECONDS))
            .build();

        return newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Retrieves the IDs of the most discussed articles from Hacker News.
     *
     * @return an array of long containing the IDs of the top stories
     * @throws IOException          if an I/O error occurs while sending or receiving the HTTP request
     * @throws InterruptedException if the sending of the HTTP request is interrupted
     * @throws URISyntaxException   if a URI syntax error occurs
     */
    public static long[] hackerNewsTopStories() throws IOException, InterruptedException, URISyntaxException {
        var response = sendHttpRequest(COMMON_URL + TOP_STORIES);

        if (response.statusCode() == GOOD_RESPONSE_STATUS) {
            String responseBody = response.body();
            return Arrays.stream(responseBody.replaceAll("[\\[\\]]", "").split(","))
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .toArray();
        } else {
            return new long[0];
        }
    }

    /**
     * Retrieves the title of a news article from Hacker News based on ID.
     *
     * @param id the ID of the news article
     * @return the title of the news article as a String
     * @throws IOException          if an I/O error occurs while sending or receiving the HTTP request
     * @throws InterruptedException if the sending of the HTTP request is interrupted
     * @throws URISyntaxException   if a URI syntax error occurs
     */
    public static String news(long id) throws IOException, URISyntaxException, InterruptedException {
        var response = sendHttpRequest(COMMON_URL + String.format(ITEM_URL, id));

        if (response.statusCode() == GOOD_RESPONSE_STATUS) {
            String responseBody = response.body();
            Pattern pattern = Pattern.compile(REGEX_TITLE);
            Matcher matcher = pattern.matcher(responseBody);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

}
