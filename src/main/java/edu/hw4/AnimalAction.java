package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class AnimalAction {
    private static final int OVERGROWTH = 100;

    private AnimalAction() {

    }

    /**
     * Задача 1: сортирует животных по росту от самого маленького к самому большому.
     *
     * @param animals список животных
     * @return отсортированный список животных
     */
    public static List<Animal> sortHeight(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .collect(Collectors.toList());
    }

    /**
     * Задача 2: сортирует животных по весу от самого
     * тяжелого к самому легкому, выбрать k первых.
     *
     * @param animals список животных
     * @param k       количество животных
     * @return отсортированный список k первых животных
     */
    public static List<Animal> sortWeight(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    /**
     * Задача 3: считает сколько животных каждого вида
     *
     * @param animals список животных
     * @return Map (key - type, value - count)
     */
    public static Map<Animal.Type, Integer> countType(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(animal -> 1)
            ));
    }

    /**
     * Задача 4: находит животное с самым длинным именем.
     *
     * @param animals список животных
     * @return Animal одно из животных с самым длинным именем
     */
    public static Animal findTheLongestName(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name()
                .length()))
            .orElse(null);
    }

    /**
     * Задача 5: находит пол, где количество животных наибольшее
     *
     * @param animals список животных
     * @return Animal.Sex преобладающий пол
     */
//    public static Animal.Sex predominantSex(@NotNull List<Animal> animals){
//        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
//    }

    /**
     * Задача 6: находит cамое тяжелое животное каждого вида
     *
     * @param animals список животных
     * @return Map (key - type, value - animal)
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
     * Задача 7: находит cамое старое животное
     *
     * @param animals список животных
     * @return Animal одно из самых старых животных
     */
    public static Animal findOldestAnimal(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::age))
            .orElse(null);
    }

    /**
     * Задача 8: находит cамое тяжелое животное среди животных ниже k см
     *
     * @param animals список животных
     * @param k       см
     * @return cамое тяжелое животное среди животных ниже k см
     */
    public static @NotNull Optional<Animal> findWeightAnimalBelowK(@NotNull List<Animal> animals, double k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    /**
     * Задача 9: находит cколько в сумме лап у животных в списке
     *
     * @param animals список животных
     * @return Integer количество лап
     */
    public static @NotNull Integer getSumOfPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws).sum();
    }

    /**
     * Задача 10: находит cписок животных, возраст у которых не совпадает с количеством лап
     *
     * @param animals список животных
     * @return список таких животных
     */
    public static List<Animal> getAgeNotMatchingPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    /**
     * Задача 11: находит список животных, которые могут укусить
     * (bites == null или true) и рост которых превышает 100 см
     *
     * @param animals список животных
     * @return список таких животных
     */
    public static List<Animal> getBitingAnimalsWithHeightGreaterThan100(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> (Objects.equals(animal.bites(), null) || animal.bites()) && animal.height() > OVERGROWTH)
            .collect(Collectors.toList());
    }

    /**
     * Задача 12: находит сколько в списке животных, вес которых превышает рост
     *
     * @param animals список животных
     * @return Integer количество животных, вес которых превышает рост
     */
    public static @NotNull Integer findCountAnimalsWithWeightGreaterThanHeight(@NotNull List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    /**
     * Задача 13: находит список животных, имена которых состоят из более чем двух слов
     *
     * @param animals список животных
     * @return список животных, имена которых состоят из более чем двух слов
     */
    public static List<Animal> findAnimalsWithNameBigThanTwoWords(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    /**
     * Задача 14: Есть ли в списке собака ростом более k см
     *
     * @param animals список животных
     * @param k       см
     * @return true or false
     */
    public static @NotNull Boolean findDogWithHeightMoreThanK(@NotNull List<Animal> animals, double k) {
        return animals.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    /**
     * Задача 15: Найти суммарный вес животных каждого вида, которым от k до l лет
     *
     * @param animals список животных
     * @param k       нижняя граница возрастов
     * @param l       верхняя граница возрастов
     * @return суммарный вес животных каждого вида, которым от k до l лет
     */
    public static @NotNull Integer findSumWeightBetweenKAndLYears(@NotNull List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() > k && animal.age() < l)
            .mapToInt(Animal::weight).sum();
    }

    /**
     * Задача 16: Список животных, отсортированный по виду, затем по полу, затем по имени
     *
     * @param animals список животных
     * @return список таких животных
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
     * Задача 17: Правда ли, что пауки кусаются чаще, чем собаки -> Boolean
     *
     * @param animals список животных
     * @return true or false
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
     * Задача 18: Найти самую тяжелую рыбку в 2-х или более списках
     *
     * @param animals список животных
     * @return самую тяжелую рыбку в 2-х или более списках
     */
    public static Animal findHaviestFishInLists(@NotNull List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH))
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    /**
     * Задача 19: Животные, в записях о которых есть ошибки: вернуть имя и список ошибок
     *
     * @param animals список животных
     * @return
     */
//    public static Map<String, Set<ValidationError>> findHaviestFishInLists(@NotNull List<List<Animal>> animals) {
//
//    }

    /**
     * Задача 20: Сделать результат предыдущего задания более читабельным:
     * вернуть имя и названия полей с ошибками, объединенные в строку
     *
     * @param animals список животных
     * @return Map <String, String>
     */
//    public static Map<String, Set<ValidationError>> findHaviestFishInLists(@NotNull List<List<Animal>> animals) {
//
//    }

}
