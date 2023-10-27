package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class AnimalAction {
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
     * @return словарь (key - type, value - count)
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
     * @return одно из животных с самым длинным именем
     */
    public static Animal findTheLongestName(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    /**
     * Задача 5: находит пол, где количество животных наибольшее
     *
     * @param animals список животных
     * @return преобладающий пол
     */
//    public static Animal.Sex predominantSex(@NotNull List<Animal> animals){
//        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
//    }

    /**
     * Задача 6: находит cамое тяжелое животное каждого вида
     *
     * @param animals список животных
     * @return словарь (key - type, value - animal)
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
     * @return одно из самых старых животных
     */
    public static Animal findOldestAnimal(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::age))
            .orElse(null);
    }

}
