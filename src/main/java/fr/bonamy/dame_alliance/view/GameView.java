package fr.bonamy.dame_alliance.view;

import fr.bonamy.dame_alliance.model.Piece;
import fr.bonamy.dame_alliance.model.PieceColor;
import fr.bonamy.dame_alliance.util.Point;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;
import java.util.Set;

public class GameView extends BorderPane {

    private BoardView boardView;

    private final ObjectProperty<Point> pieceClickedProperty = new SimpleObjectProperty<>(); // Observable for pieces

    public GameView(BoardView boardView) {
        super();
        this.boardView = boardView;
        this.setCenter(boardView);
    }

    public void renderPieces(Map<Point, Piece> pieces, PieceColor activeColor) {
        this.boardView.getChildren().removeIf(node -> node instanceof PieceView);
        for (Map.Entry<Point, Piece> pieceEntry : pieces.entrySet()) {
            if (pieceEntry.getValue().isActive()) {
                PieceView pieceView = new PieceView(pieceEntry.getValue().getColor(), pieceEntry.getValue().getType());
                Point point = pieceEntry.getKey();
                if (pieceEntry.getValue().getColor().equals(activeColor)) {
                    pieceView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        pieceClickedProperty.set(point);
                        event.consume();
                    });
                }
                this.getBoardView().add(pieceView, point.getA(), point.getB());
            }
        }
    }

    public void highlightValidTile(Set<Point> validMoves) {
        this.boardView.getChildren().stream().filter(node -> node instanceof Rectangle)
                .filter(tile -> validMoves.contains(new Point(GridPane.getColumnIndex(tile), GridPane.getRowIndex(tile))))
                .forEach(tile -> ((Rectangle) tile).setFill(Color.YELLOW));
    }

    public ObjectProperty<Point> pieceClickedPropertyProperty() {
        return pieceClickedProperty;
    }

    public BoardView getBoardView() {
        return boardView;
    }

}
