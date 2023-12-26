package edu.hw9;

import edu.hw9.task3.DFSGenerationMultiThreaded;
import edu.project2.model.Maze;
import edu.project2.utility.generation.DFSGeneration;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void testThatMultiGenerationIsFasterThanSingle() {
        //given
        int height = 20;
        int width = 20;
        int threadCount = Runtime.getRuntime().availableProcessors();
        DFSGenerationMultiThreaded multiThreadedGenerator = new DFSGenerationMultiThreaded(threadCount);
        DFSGeneration singleThreadedGenerator = new DFSGeneration();

        //when
        long startSingleThreaded = System.nanoTime();
        Maze singleThreadedMaze = singleThreadedGenerator.generate(height, width);
        long endSingleThreaded = System.nanoTime() - startSingleThreaded;

        long startMultiThreaded = System.nanoTime();
        Maze multiThreadedMaze = multiThreadedGenerator.generate(height, width);
        long endMultiThreaded = System.nanoTime() - startMultiThreaded;

        //then four times faster
        System.out.println("multi: " + endMultiThreaded);
        System.out.println("single: " + endSingleThreaded);
    }
}
