package edu.hw4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestAnimalAction {
    private static @NotNull List<Animal> createAnimals() {
        return Arrays.asList(
            new Animal("Ronaldo", Animal.Type.CAT, Animal.Sex.M, 4, 101, 5, true),
            new Animal("Samba", Animal.Type.CAT, Animal.Sex.F, 3, 20, 4, false),
            new Animal("Mars", Animal.Type.DOG, Animal.Sex.M, 5, 30, 8, true),
            new Animal("Tasya", Animal.Type.DOG, Animal.Sex.F, 4, 28, 7, true),
            new Animal("Fifi", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false),
            new Animal("Semion", Animal.Type.SPIDER, Animal.Sex.M, 2, 2, 0, true)
        );
    }

    @Test
    void testThatSortHeightReturnedAnimalsSortedByHeightAscending1() {
        List<Animal> animals = createAnimals();
        List<Animal> sortedAnimals = AnimalAction.sortHeight(animals);

        for (int i = 0; i < sortedAnimals.size() - 1; i++) {
            int currentHeight = sortedAnimals.get(i).height();
            int nextHeight = sortedAnimals.get(i + 1).height();
            assertThat(currentHeight).isLessThanOrEqualTo(nextHeight);
        }
    }

    @Test
    void testThatSortWeightReturnedAnimalsSortByWeightDescending2() {
        List<Animal> animals = createAnimals();
        int k = 3;
        List<Animal> sortedAnimals = AnimalAction.sortWeight(animals, k);

        for (int i = 0; i < k - 1; i++) {
            int currentWeight = sortedAnimals.get(i).height();
            int nextWeight = sortedAnimals.get(i + 1).height();
            assertThat(currentWeight).isGreaterThan(nextWeight);
        }
    }

    @Test
    void testThatCountTypeReturnedCorrectMap3() {
        List<Animal> animals = createAnimals();

        Map<Animal.Type, Integer> expectedCount = new HashMap<>();
        expectedCount.put(Animal.Type.CAT, 2);
        expectedCount.put(Animal.Type.DOG, 2);
        expectedCount.put(Animal.Type.BIRD, 1);
        expectedCount.put(Animal.Type.FISH, 1);
        expectedCount.put(Animal.Type.SPIDER, 1);

        Map<Animal.Type, Integer> count = AnimalAction.countType(animals);

        assertThat(count).isEqualTo(expectedCount);
    }

    @Test
    void testThatFindTheLongestNameReturnedCorrectRes4() {
        List<Animal> animals = createAnimals();
        Animal animalWithLongestName = AnimalAction.findTheLongestName(animals);

        int lenghtOfName = (animalWithLongestName.name()).length();
        int expectedLenght = 7;

        assertThat(lenghtOfName).isEqualTo(expectedLenght);
    }

    @Test
    void testThatHeaviestAnimalSpeciesReturnedMapOfTypeAndHeaviestAnimal6() {
        List<Animal> animals = createAnimals();
        Map<Animal.Type, Animal> expectedHeaviestAnimals = new HashMap<>();
        expectedHeaviestAnimals.put(
            Animal.Type.CAT,
            new Animal("Ronaldo", Animal.Type.CAT, Animal.Sex.M, 4, 101, 5, true)
        );
        expectedHeaviestAnimals.put(
            Animal.Type.DOG,
            new Animal("Mars", Animal.Type.DOG, Animal.Sex.M, 5, 30, 8, true)
        );
        expectedHeaviestAnimals.put(
            Animal.Type.BIRD,
            new Animal("Fifi", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false)
        );
        expectedHeaviestAnimals.put(
            Animal.Type.FISH,
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false)
        );
        expectedHeaviestAnimals.put(
            Animal.Type.SPIDER,
            new Animal("Semion", Animal.Type.SPIDER, Animal.Sex.M, 2, 2, 0, true)
        );

        Map<Animal.Type, Animal> heaviestAnimals = AnimalAction.findHeaviestAnimalSpecies(animals);

        assertThat(heaviestAnimals).isEqualTo(expectedHeaviestAnimals);
    }

    @Test
    public void testThatFindOldestAnimalReturnedCorrectRes7() {
        List<Animal> animals = createAnimals();
        Animal oldestAnimal = AnimalAction.findOldestAnimal(animals);

        int maxAge = oldestAnimal.age();
        int expectedAge = 5;

        assertThat(maxAge).isEqualTo(expectedAge);
    }

    @Test
    void testThatFindWeightestAnimalBelowKReturnedCorrectRes8() {
        List<Animal> animals = createAnimals();
        double k = 10.1;

        Animal expectedAnimal = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false);

        Optional<Animal> weightestAnimalBelowK = AnimalAction.findWeightAnimalBelowK(animals, k);

        assertThat(weightestAnimalBelowK).isPresent();
        assertThat(weightestAnimalBelowK.get()).isEqualTo(expectedAnimal);
    }

    @Test
    public void testThatGetSumOfPawsReturnedCorrectSum9() {
        List<Animal> animals = createAnimals();
        int expectedSum = 26;

        int actualSum = AnimalAction.getSumOfPaws(animals);

        assertThat(actualSum).isEqualTo(expectedSum);
    }

    @Test
    public void testThatGetAgeNotMatchingPawsReturnedCorrectList10() {
        List<Animal> animals = createAnimals();
        List<Animal> expectedList = Arrays.asList(
            new Animal("Samba", Animal.Type.CAT, Animal.Sex.F, 3, 20, 4, false),
            new Animal("Mars", Animal.Type.DOG, Animal.Sex.M, 5, 30, 8, true),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false),
            new Animal("Semion", Animal.Type.SPIDER, Animal.Sex.M, 2, 2, 0, true)
        );

        List<Animal> actualList = AnimalAction.getAgeNotMatchingPaws(animals);

        assertThat(actualList).isEqualTo(expectedList);
    }

    @Test
    public void testThatGetBitingAnimalsWithHeightGreaterThan100ReturnedCorrectList11() {
        List<Animal> animals = createAnimals();
        List<Animal> expectedList = List.of(
            new Animal("Ronaldo", Animal.Type.CAT, Animal.Sex.M, 4, 101, 5, true));

        List<Animal> actualList = AnimalAction.getBitingAnimalsWithHeightGreaterThan100(animals);

        assertThat(actualList).isEqualTo(expectedList);
    }

    @Test
    public void testThatFindCountAnimalsWithWeightGreaterThanHeightReturnedCorrectCount12() {
        List<Animal> animals = createAnimals();
        int expectedCount = 1;

        int actualCount = AnimalAction.findCountAnimalsWithWeightGreaterThanHeight(animals);

        assertThat(actualCount).isEqualTo(expectedCount);
    }

}
