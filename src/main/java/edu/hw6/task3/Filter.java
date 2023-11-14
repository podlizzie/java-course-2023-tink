package edu.hw6.task3;

import java.nio.file.Files;
import java.nio.file.Path;

public class Filter {

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;

    private Filter() {
    }

    /**
     * Create a filter that matches files larger than or equal to the specified size.
     *
     * @param requireSize The minimum size.
     * @return An AbstractFilter that matches files larger than or equal to the specified size.
     */
    public static AbstractFilter largerThan(int requireSize) {
        return path -> Files.size(path) >= requireSize;
    }

    /**
     * Create a filter that matches files containing a specific magic number.
     *
     * @param numbers The magic number to match as an array of characters.
     * @return An AbstractFilter that matches files containing the specified magic number.
     */
    public static AbstractFilter magicNumber(char... numbers) {
        return path -> {
            var bytes = Files.readAllBytes(path);
            if (bytes.length < numbers.length) {
                return false;
            }
            for (int i = 0; i < numbers.length; i++) {
                if (bytes[i] != (byte) numbers[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * Create a filter that matches files based on their extension.
     *
     * @param glob File extension to be matched.
     * @return An AbstractFilter that matches files with glob extension.
     */
    public static AbstractFilter globMatches(String glob) {
        return (Path path) -> {
            String fileName = path.getFileName().toString();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            String requiredExtension = glob.substring(glob.lastIndexOf(".") + 1);
            return fileExtension.equals(requiredExtension);
        };
    }

    /**
     * Create a filter that matches files based on their name using a regular expression.
     *
     * @param regex The regular expression to match the file names.
     * @return An AbstractFilter that matches files based on the regular expression.
     */
    public static AbstractFilter regexContains(String regex) {
        return (Path path) -> path.getFileName().toString().matches(regex);
    }

}
