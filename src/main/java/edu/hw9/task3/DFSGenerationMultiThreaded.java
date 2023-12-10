package edu.hw9.task3;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.utility.generation.GenerationUtils;
import edu.project2.utility.generation.Generator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DFSGenerationMultiThreaded implements Generator {
    private final ExecutorService executorService;

    public DFSGenerationMultiThreaded(int threadCount) {
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    @Override
    public Maze generate(int height, int width) {
        GenerationUtils.checkInput(height, width);

        Cell[][] grid = new Cell[height][width];
        GenerationUtils.initializeMaze(height, width, grid);

        Random random = new Random();
        Coordinate start = new Coordinate(0, 0);
        grid[start.row()][start.col()].setType(Cell.Type.PASSAGE);

        executorService.submit(() -> dfs(grid, start, random));

        executorService.shutdown();

        return new Maze(height, width, grid);
    }

    private void dfs(Cell[][] grid, Coordinate current, Random random) {
        Stack<Coordinate> stack = new Stack<>();
        stack.push(current);

        while (!stack.isEmpty()) {
            current = stack.peek();
            List<Coordinate> neighbors = GenerationUtils.getNeighbors(current, grid);
            if (!neighbors.isEmpty()) {
                Coordinate next = neighbors.get(random.nextInt(neighbors.size()));
                removeWall(current, next, grid);
                grid[next.row()][next.col()].setType(Cell.Type.PASSAGE);

                executorService.submit(() -> dfs(grid, next, new Random(random.nextLong())));
                stack.push(next);
            } else {
                stack.pop();
            }
        }
    }

    private void removeWall(Coordinate current, Coordinate next, Cell[][] grid) {
        int row = (current.row() + next.row()) / 2;
        int col = (current.col() + next.col()) / 2;
        synchronized (grid) {
            grid[row][col].setType(Cell.Type.PASSAGE);
        }
    }
}
