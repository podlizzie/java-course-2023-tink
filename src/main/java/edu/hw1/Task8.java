package edu.hw1;

public class Task8 {
    private static final int SIZE = 8;
    private static final int[] DX = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] DY = {1, 2, 2, 1, -1, -2, -2, -1};

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        int[][] newBoard = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 1) {
                    addMoves(i, j, newBoard);
                }
            }
        }
        return isCaptured(board, newBoard);
    }

    private static void addMoves(int x, int y, int[][] newBoard) {
        for (int i = 0; i < SIZE; i++) {
            int dx = x + DX[i];
            int dy = y + DY[i];
            if (dx >= 0 && dx < SIZE && dy >= 0 && dy < SIZE) {
                newBoard[dx][dy]++;
            }
        }
    }

    private static boolean isCaptured(int[][] board, int[][] newBoard) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 1 && newBoard[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
