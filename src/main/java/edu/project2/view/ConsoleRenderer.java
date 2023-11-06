package edu.project2.view;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ConstantName")
public class ConsoleRenderer implements Renderer {

    private static final String wallSymbol = "▒";
    private static final String passageSymbol = " ";
    private static final String pathSymbol = "*";
    private static final String startSymbol = "S";
    private static final String endSymbol = "E";
    private static final int COORDINATE_START = 0;

    /**
     * Renders the maze without a solution path.
     *
     * @param maze the maze to render
     * @return the rendered maze as a string
     */
    @Override
    public String render(Maze maze) {
        return renderMaze(maze, null);
    }

    /**
     * Renders the maze with a solution path.
     *
     * @param maze         the maze to render
     * @param solutionPath the solution path
     * @return the rendered maze with the solution path as a string
     */
    @Override
    public String render(Maze maze, List<Coordinate> solutionPath) {
        return renderMaze(maze, solutionPath);
    }

    /**
     * Renders the maze with or without a solution path.
     *
     * @param maze         the maze to render
     * @param solutionPath the solution path, can be null if no solution path should be rendered
     * @return the rendered maze as a string
     */
    private String renderMaze(Maze maze, List<Coordinate> solutionPath) {
        StringBuilder sb = new StringBuilder();
        Cell[][] grid = maze.grid();
        int height = maze.height();
        int width = maze.width();

        // Upper border printing
        sb.append("━".repeat(width * 2 + 1)).append(System.lineSeparator());

        for (int row = 0; row < height; row++) {
            sb.append("┃"); // Left border
            for (int col = 0; col < width; col++) {
                Cell cell = grid[row][col];
                Coordinate coordinate = new Coordinate(row, col);

                String symbol = getSymbol(solutionPath, coordinate, cell);

                sb.append(symbol).append(passageSymbol);
            }
            sb.append("┃"); // Right border
            sb.append(System.lineSeparator());
        }

        // Lower border printing
        sb.append("━".repeat(width * 2 + 1)).append(System.lineSeparator());

        return sb.toString();
    }

    /**
     * Determines the symbol to be rendered for a given coordinate in the maze based on the solution path and cell type.
     *
     * @param solutionPath the solution path, can be null if no solution path should be rendered
     * @param coordinate   the coordinate for which to determine the symbol
     * @param cell         the cell at the given coordinate
     * @return the symbol to be rendered for the given coordinate
     */

    @NotNull private static String getSymbol(List<Coordinate> solutionPath, Coordinate coordinate, Cell cell) {
        String symbol = passageSymbol;

        if (solutionPath != null && solutionPath.contains(coordinate)) {
            symbol = pathSymbol;
        } else if (cell.getType() == Cell.Type.WALL) {
            symbol = wallSymbol;
        }

        if (solutionPath != null && coordinate.equals(solutionPath.get(COORDINATE_START))) {
            symbol = startSymbol;
        } else if (solutionPath != null && coordinate.equals(solutionPath.get(solutionPath.size() - 1))) {
            symbol = endSymbol;
        }
        return symbol;
    }
}
