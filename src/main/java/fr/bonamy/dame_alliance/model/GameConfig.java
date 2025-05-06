package fr.bonamy.dame_alliance.model;

public class GameConfig {

    private static int boardSize = 8;
    private static int pieceNumber = 12;

    public static int getBoardSize() {
        return boardSize;
    }

    public static void setBoardSize(int boardSize) {
        GameConfig.boardSize = boardSize;
    }

    public static int getPieceNumber() {
        return pieceNumber;
    }

    public static void setPieceNumber(int pieceNumber) {
        GameConfig.pieceNumber = pieceNumber;
    }
}
