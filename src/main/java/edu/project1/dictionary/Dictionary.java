package edu.project1.dictionary;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface Dictionary {
    @NotNull
    String randomWord();

    @NotNull
    List<String> dict();
}
