package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChainConstruction {
    private final static Logger LOGGER = LogManager.getLogger();

    private ChainConstruction() {

    }

    public static void writeTextToFile(String filePath, String text) {
        try (OutputStream fileOutputStream = Files.newOutputStream(Paths.get(filePath));
             OutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                 bufferedOutputStream,
                 StandardCharsets.UTF_8
             );
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.println(text);
        } catch (IOException e) {
            LOGGER.info(e);
        }
    }
}
