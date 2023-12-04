package edu.hw9.task3.utility.generation;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGeneration implements Generator {

    private final Random random = new Random();

    /**
     * Generates a maze using height and width.
     *
     * @param height the height of the maze
     * @param width  the width of the maze
     * @return the generated maze
     */
    @Override
    public Maze generate(int height, int width) {
        GenerationUtils.checkInput(height, width);

        Cell[][] grid = new Cell[height][width];
        Maze maze = GenerationUtils.initializeMaze(height, width, grid);
        List<Coordinate> path = new ArrayList<>();

        // Choose a random starting cell
        Coordinate startCell = getRandomCell(maze);
        path.add(startCell);
        maze.grid()[startCell.row()][startCell.col()] = new Cell(startCell.row(), startCell.col(), Cell.Type.PASSAGE);

        generateMazeUsingPrimAlgorithm(path, maze);

        return maze;
    }

    private void generateMazeUsingPrimAlgorithm(List<Coordinate> path, Maze maze) {
        while (!path.isEmpty()) {
            Coordinate currentCell = path.remove(random.nextInt(path.size()));
            List<Coordinate> neighbors = GenerationUtils.getNeighbors(currentCell, maze.grid());

            if (!neighbors.isEmpty()) {
                Coordinate neighbor = neighbors.get(random.nextInt(neighbors.size()));
                Coordinate between1 = new Coordinate(
                    (currentCell.row() + neighbor.row()) / 2,
                    (currentCell.col() + neighbor.col()) / 2
                );
                Coordinate between2 = new Coordinate(
                    (currentCell.row() + neighbor.row()) / 2,
                    (currentCell.col() + neighbor.col()) / 2
                );

                maze.grid()[between1.row()][between1.col()] =
                    new Cell(between1.row(), between1.col(), Cell.Type.PASSAGE);
                maze.grid()[between2.row()][between2.col()] =
                    new Cell(between2.row(), between2.col(), Cell.Type.PASSAGE);

                path.add(currentCell);
                path.add(neighbor);
                maze.grid()[neighbor.row()][neighbor.col()] =
                    new Cell(neighbor.row(), neighbor.col(), Cell.Type.PASSAGE);
            }
        }
    }

    private Coordinate getRandomCell(Maze maze) {
        int row = random.nextInt(maze.height());
        int col = random.nextInt(maze.width());
        return new Coordinate(row, col);
    }
}
