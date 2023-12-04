package edu.hw9.task3;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.utility.generation.PrimGeneration;
import edu.project2.utility.solution.DFSSolver;
import edu.project2.view.ConsoleRenderer;
import java.util.List;

public class Main {
    private Main() {

    }

    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public static void main(String[] args) {
        //example
        int height = 15;
        int width = 15;
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(14, 14);

        PrimGeneration generator = new PrimGeneration();
        Maze maze = generator.generate(height, width);
        ConsoleRenderer renderer = new ConsoleRenderer();
        System.out.println(renderer.render(maze));

        DFSSolver dfsSolver = new DFSSolver();
        List<Coordinate> path = dfsSolver.solve(maze, start, end);
        System.out.println(renderer.render(maze, path));

    }
}
