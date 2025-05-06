package fr.bonamy.dame_alliance.view;

import fr.bonamy.dame_alliance.model.GameConfig;
import fr.bonamy.dame_alliance.util.Point;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardView extends GridPane {

    private final static int BOARD_SIZE = GameConfig.getBoardSize();
    private final static int TILE_SIZE_PIXEL = 81;

    private final ObjectProperty<Point> tileClickedProperty = new SimpleObjectProperty<>(); // Observable for tiles

    public BoardView() {
        super();
        this.setPadding(new Insets(20));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Rectangle tile = new Rectangle(TILE_SIZE_PIXEL, TILE_SIZE_PIXEL);
                if ((j % 2 == 0 && i % 2 == 0) || (j % 2 != 0 && i % 2 != 0)) {
                    tile.setFill(Color.BEIGE);
                } else {
                    tile.setFill(Color.BLACK);
                }
                tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    int col = GridPane.getColumnIndex(tile);
                    int row = GridPane.getRowIndex(tile);
                    tileClickedProperty.set(new Point(col, row));
                    event.consume();
                });
                this.add(tile, j, i);
            }
        }
    }

    public void clearBoard() {
        this.getChildren().stream().filter(node -> node instanceof Rectangle)
                .forEach(tile -> ((Rectangle) tile)
                        .setFill((GridPane.getColumnIndex(tile) + GridPane.getRowIndex(tile)) % 2 == 0
                                ? Color.BEIGE : Color.BLACK));
    }

    public ObjectProperty<Point> tileClickedProperty() {
        return tileClickedProperty;
    }

}
