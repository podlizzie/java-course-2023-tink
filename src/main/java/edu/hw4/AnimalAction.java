package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class AnimalAction {
    private static final int OVERGROWTH = 100;

    private AnimalAction() {

    }

    /**
     * Task 1: Sorts animals by height from smallest to largest.
     *
     * @param animals the list of animals
     * @return the sorted list of animals
     */
    public static List<Animal> sortHeight(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .collect(Collectors.toList());
    }

    /**
     * Task 2: Sorts animals by weight from heaviest to lightest and selects the first k animals.
     *
     * @param animals the list of animals
     * @param k       the number of animals to select
     * @return the sorted list of the first k animals
     */
    public static List<Animal> sortWeight(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    /**
     * Task 3: Counts the number of animals for each type.
     *
     * @param animals the list of animals
     * @return a map containing animal types as keys and the count of animals as values
     */
    public static Map<Animal.Type, Integer> countType(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(animal -> 1)
            ));
    }

    /**
     * Task 4: Finds the animal with the longest name.
     *
     * @param animals the list of animals
     * @return one of the animals with the longest name
     */
    public static Animal findTheLongestName(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name()
                .length()))
            .orElse(null);
    }

    /**
     * Task 5: Finds the predominant sex, i.e., the sex with the highest count of animals.
     *
     * @param animals the list of animals
     * @return the predominant sex (Animal.Sex)
     */
    public static Animal.Sex predominantSex(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    /**
     * Task 6: Finds the heaviest animal of each type.
     *
     * @param animals the list of animals
     * @return a map containing animal types as keys and the heaviest animal of each type as values
     */
    public static Map<Animal.Type, Animal> findHeaviestAnimalSpecies(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (animal1, animal2) -> animal2.weight() > animal1.weight() ? animal2 : animal1
            ));
    }

    /**
     * Task 7: Finds the oldest animal.
     *
     * @param animals the list of animals
     * @return one of the oldest animals
     */
    public static Animal findOldestAnimal(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::age))
            .orElse(null);
    }

    /**
     * Task 8: Finds the heaviest animal among the animals below the given height (k).
     *
     * @param animals the list of animals
     * @param k       the maximum height under which the animal should be
     * @return the heaviest animal below the given height
     */
    public static @NotNull Optional<Animal> findWeightAnimalBelowK(@NotNull List<Animal> animals, double k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    /**
     * Task 9: Calculates the sum of paws for all animals in the list.
     *
     * @param animals the list of animals
     * @return the sum of paws
     */
    public static @NotNull Integer getSumOfPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws).sum();
    }

    /**
     * Task 10: Finds the list of animals whose age does not match the number of paws.
     *
     * @param animals the list of animals
     * @return the list of animals whose age does not match the number of paws
     */
    public static List<Animal> getAgeNotMatchingPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    /**
     * Task 11: Finds the list of animals that can bite (bites == null or true) and have a height greater than 100 cm.
     *
     * @param animals the list of animals
     * @return the list of animals that can bite and have a height greater than 100 cm
     */
    public static List<Animal> getBitingAnimalsWithHeightGreaterThan100(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> (Objects.equals(animal.bites(), null) || animal.bites()) && animal.height() > OVERGROWTH)
            .collect(Collectors.toList());
    }

    /**
     * Task 12: Finds the count of animals whose weight exceeds their height.
     *
     * @param animals the list of animals
     * @return the count of animals whose weight exceeds their height
     */
    public static @NotNull Integer findCountAnimalsWithWeightGreaterThanHeight(@NotNull List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    /**
     * Task 13: Finds the list of animals whose names consist of more than two words.
     *
     * @param animals the list of animals
     * @return the list of animals whose names consist of more than two words
     */
    public static List<Animal> findAnimalsWithNameBigThanTwoWords(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    /**
     * Task 14: Checks if there is a dog in the list with a height greater than k centimeters.
     *
     * @param animals the list of animals
     * @param k       the height in centimeters
     * @return true or false
     */
    public static @NotNull Boolean findDogWithHeightMoreThanK(@NotNull List<Animal> animals, double k) {
        return animals.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    /**
     * Task 15: Calculates the sum of weights of the animals of each type, whose age is between k and l.
     *
     * @param animals the list of animals
     * @param k       the lower age limit
     * @param l       the upper age limit
     * @return the sum of weights of animals of each type, within the age range [k, l]
     */
    public static @NotNull Integer findSumWeightBetweenKAndLYears(@NotNull List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() > k && animal.age() < l)
            .mapToInt(Animal::weight).sum();
    }

    /**
     * Task 16: Sorts the list of animals by type, then by sex, then by name.
     *
     * @param animals the list of animals
     * @return the sorted list of animals
     */
    public static List<Animal> sortAnimalsByTypeSexAndName(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    /**
     * Task 17: Checks if spiders bite more often than dogs.
     *
     * @param animals the list of animals
     * @return true if spiders bite more often than dogs, false otherwise
     */
    public static @NotNull Boolean areSpidersBitesMoreThanDogs(@NotNull List<Animal> animals) {
        long biteDogs = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.DOG) && animal.bites())
            .count();

        long biteSpiders = animals.stream()
            .filter(animal -> animal.type().equals(Animal.Type.SPIDER) && animal.bites())
            .count();

        return biteSpiders > biteDogs;
    }

    /**
     * Task 18: Finds the heaviest fish among the animals in 2 or more lists.
     *
     * @param animals the list of animal lists
     * @return the heaviest fish among the animals in the lists
     */
    public static Animal findHaviestFishInLists(@NotNull List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH))
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    /**
     * Task 19: Finds the animals in the list that have errors in their records and returns
     * their names and the list of errors.
     *
     * @param animals the list of animals
     * @return the map containing the animal names as keys and the set of errors as values
     */
    public static Map<String, Set<ValidationError>> findErrors(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                AnimalValidation::validateAnimal,
                (currentErrors, newErrors) -> {
                    currentErrors.addAll(newErrors);
                    return currentErrors;
                }
            ));
    }

    /**
     * Task 20: Makes the result of the previous task more readable by returning the animal
     * names and the field names with errors as a human-readable string.
     *
     * @param animals the list of animals
     * @return the map containing the animal names as keys and the
     * concatenated string of field names with errors as values
     */
    public static Map<String, String> findReadableErrors(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                animal -> AnimalValidation.validateAnimal(animal).stream()
                    .map(error -> String.format("%s : %s", error.getBody(), error.getErrorMessage()))
                    .collect(Collectors.joining(", "))
            ));
    }
}
