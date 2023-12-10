package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private Server() {

    }

    private static final int PORT = 1704;
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int MAX_CONNECTIONS = 5;
    private static final String QUOTES_NOT_FOUND = "Цитата не найдена.";
    private static final String SERVER_RUN = "Server is running";
    private static final String CLIENT_CONNECT = "Сlient connected";
    private static final Map<String, String> QUOTES = Map.of(
        "личности",
        "Не переходи на личности там, где их нет.",
        "оскорбления",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
        "глупый",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект",
        "Чем ниже интеллект, тем громче оскорбления."
    );

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info(SERVER_RUN);
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                LOGGER.info(CLIENT_CONNECT);
                executor.submit(() -> {
                    try {
                        handleClient(connectionSocket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } finally {
            executor.shutdown();
        }
    }

    private static void handleClient(Socket connectionSocket) throws IOException {
        try (connectionSocket) {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);

            String word = inFromClient.readLine();
            String quote = getQuoteForKeyword(word);

            outToClient.println(quote);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static String getQuoteForKeyword(String word) {
        return QUOTES.getOrDefault(word.toLowerCase(), QUOTES_NOT_FOUND);
    }
}
