package fr.bonamy.dame_alliance.model;

import fr.bonamy.dame_alliance.util.Point;

public class Board {

    private static final int SIZE = GameConfig.getBoardSize();

    private Point[][] board;

    public Board() {
        board = new Point[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Point(i, j);
            }
        }
    }

    public Point[][] getBoard() {
        return board;
    }

    public void setBoard(Point[][] board) {
        this.board = board;
    }
}
