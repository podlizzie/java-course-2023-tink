package edu.hw4;

import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class AnimalValidation {
    private static final int MIN_VALUE = 0;
    private static final int MAX_AGE = 50;
    private static final int MAX_HEIGHT = 150;
    private static final int MAX_WEIGHT = 100;
    private static final String INVALID_PARAM = "Invalid param: ";

    private AnimalValidation() {

    }

    public static @NotNull Set<ValidationError> validateAnimal(@NotNull Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.age() < MIN_VALUE || animal.age() > MAX_AGE) {
            ValidationError error = new ValidationError(INVALID_PARAM + animal.age());
            error.setBody("age");
            errors.add(error);
        }

        if (animal.height() < MIN_VALUE || animal.height() > MAX_HEIGHT) {
            ValidationError error = new ValidationError(INVALID_PARAM + animal.height());
            error.setBody("height");
            errors.add(error);
        }

        if (animal.weight() <= MIN_VALUE || animal.weight() > MAX_WEIGHT) {
            ValidationError error = new ValidationError(INVALID_PARAM + animal.weight());
            error.setBody("weight");
            errors.add(error);
        }

        return errors;
    }
}
