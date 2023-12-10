package edu.hw9;

import edu.hw9.task3.DFSGenerationMultiThreaded;
import edu.project2.model.Maze;
import edu.project2.utility.generation.DFSGeneration;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    public void testThatMultiGenerationIsFasterThanSingle() {
        //given
        final int height = 20;
        final int width = 20;
        int threadCount = Runtime.getRuntime().availableProcessors();
        DFSGenerationMultiThreaded multiThreadedGenerator = new DFSGenerationMultiThreaded(threadCount);
        DFSGeneration singleThreadedGenerator = new DFSGeneration();

        //when
        long startSingleThreaded = System.nanoTime();
        Maze singleThreadedMaze = singleThreadedGenerator.generate(height, width);
        long endSingleThreaded = System.nanoTime();

        long startMultiThreaded = System.nanoTime();
        Maze multiThreadedMaze = multiThreadedGenerator.generate(height, width);
        long endMultiThreaded = System.nanoTime();

        //then
        assertThat(endMultiThreaded - startMultiThreaded).isLessThan(endSingleThreaded - startSingleThreaded);
    }
}
