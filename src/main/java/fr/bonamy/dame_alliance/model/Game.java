package fr.bonamy.dame_alliance.model;

import fr.bonamy.dame_alliance.util.Point;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private Board board;
    private Map<Point, Piece> pieces;
    private String player1 = "player 1";
    private String player2 = "player 2";
    private boolean whiteTurn = true;

    public Game() {
        this.board = new Board();
        initPieces();
    }

    public void initPieces() {
        pieces = new HashMap<>();
        for (int i = 1; i < 7; i++) {
            pieces.put(new Point(i, 1), new Piece(PieceColor.WHITE, PieceType.ORTHOGONAL));
            pieces.put(new Point(i, 2), new Piece(PieceColor.WHITE, PieceType.DIAGONAL));
            pieces.put(new Point(i, 5), new Piece(PieceColor.BLACK, PieceType.DIAGONAL));
            pieces.put(new Point(i, 6), new Piece(PieceColor.BLACK, PieceType.ORTHOGONAL));
        }
    }

    public boolean movePiece(Piece piece, Point from, Point to, MoveValidator moveValidator) {
        var isMoveValid = moveValidator.isMoveValid(piece, from, to, pieces);
        //TODO: Verifier si le mvt a eu lieu.
        if (isMoveValid) {
            pieces.remove(from, piece);
            pieces.put(to, piece);
            if (moveValidator.canBePromoted(piece, to)) {
                piece.makeKing();
            }
            moveValidator.capture(piece, from, to, pieces);
        }
        System.out.println("whiteTurn = " + whiteTurn);
        whiteTurn = !whiteTurn;
        return isMoveValid;
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Map<Point, Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Map<Point, Piece> pieces) {
        this.pieces = pieces;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", pieces=" + pieces +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", whiteTurn=" + whiteTurn +
                '}';
    }
}
