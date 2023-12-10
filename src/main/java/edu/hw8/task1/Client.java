package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private Client() {

    }

    private static final String SERVER = "localhost";
    private static final String INPUT_PHRASE = "Ваня: ";
    private static final String OUTPUT_PHRASE = "Сервер: %s";
    private final static Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 1704;

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER, PORT)) {
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            String keyWord = getUserWord();
            outToServer.println(keyWord);

            var answer = getAnswerFromServer(socket);
            LOGGER.info(String.format(OUTPUT_PHRASE, answer));
        } catch (ConnectException e) {
            LOGGER.error("Connection refused. Please ensure the server is running.");
        } catch (IOException e) {
            LOGGER.error("An error occurred while communicating with the server.");
        }
    }

    public static String getAnswerFromServer(Socket socket) throws IOException {
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return inFromServer.readLine();
    }

    private static String getUserWord() throws IOException {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        LOGGER.info(INPUT_PHRASE);
        return userInput.readLine();
    }
}
