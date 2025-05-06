package fr.bonamy.dame_alliance.controller;

import fr.bonamy.dame_alliance.model.*;
import fr.bonamy.dame_alliance.util.Point;
import fr.bonamy.dame_alliance.view.GameView;

public class GameController {

    private final GameView gameView;
    private final Game game;

    private Point selectedPiecePosition;
    private final MoveValidator moveValidator = new AllianceMoveValidator();

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        this.gameView.renderPieces(game.getPieces(), game.isWhiteTurn() ? PieceColor.WHITE : PieceColor.BLACK);
        this.gameView.getBoardView().tileClickedProperty().addListener(
                ((_, _, newValue) -> handleTileClick(newValue)));

        this.gameView.pieceClickedPropertyProperty().addListener(
                ((_, _, newValue) -> handlePieceClick(newValue)));
    }

    private void handlePieceClick(Point point) {
        this.gameView.getBoardView().clearBoard();
        selectedPiecePosition = point;
        Piece piece = game.getPieces().get(selectedPiecePosition);
        gameView.highlightValidTile(moveValidator.getValidMoves(piece, point, game.getPieces()));
    }

    private void handleTileClick(Point tilePosition) {
        this.gameView.getBoardView().clearBoard();
        if (selectedPiecePosition != null) {
            Piece piece = game.getPieces().get(selectedPiecePosition);
            if (game.movePiece(piece, selectedPiecePosition, tilePosition, moveValidator)) {
                selectedPiecePosition = null;
                gameView.renderPieces(game.getPieces(), game.isWhiteTurn() ? PieceColor.WHITE : PieceColor.BLACK);
            }
        }
    }

}
