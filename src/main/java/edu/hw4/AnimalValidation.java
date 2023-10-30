package edu.hw4;

import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class AnimalValidation {
    private AnimalValidation() {

    }

    public static @NotNull Set<ValidationError> validateAnimal(@NotNull Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.age() < 0 || animal.age() > 50) {
            ValidationError error = new ValidationError("Invalid param: " + animal.age());
            error.setBody("age");
            errors.add(error);
        }

        if (animal.height() < 0 || animal.height() > 150) {
            ValidationError error = new ValidationError("Invalid param: " + animal.height());
            error.setBody("height");
            errors.add(error);
        }

        if (animal.weight() <= 0 || animal.weight() > 100) {
            ValidationError error = new ValidationError("Invalid param: " + animal.weight());
            error.setBody("weight");
            errors.add(error);
        }

        return errors;
    }
}
