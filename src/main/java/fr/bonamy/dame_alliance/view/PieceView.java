package fr.bonamy.dame_alliance.view;

import fr.bonamy.dame_alliance.model.PieceColor;
import fr.bonamy.dame_alliance.model.PieceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PieceView extends ImageView {

    private final static String WHITE_PIECE_PATH = "/images/white_with_simple_cross.png";
    private final static String BLACK_PIECE_PATH = "/images/black_with_simple_cross.png";
    private final static String WHITE_KING_PATH = "/images/white_simple.png";
    private final static String BLACK_KING_PATH = "/images/black_simple.png";


    private final PieceColor pieceColor;
    private PieceType pieceType;
    private Image pieceImage;

    public PieceView(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        if (pieceColor == PieceColor.BLACK) {
            if (pieceType == PieceType.KING) {
                this.pieceImage =
                        new Image(Objects.requireNonNull(this.getClass().getResource(BLACK_KING_PATH)).toExternalForm());
            } else {
                this.pieceImage =
                        new Image(Objects.requireNonNull(this.getClass().getResource(BLACK_PIECE_PATH)).toExternalForm());
            }
        } else {
            if (pieceType == PieceType.KING) {
                this.pieceImage =
                        new Image(Objects.requireNonNull(this.getClass().getResource(WHITE_KING_PATH)).toExternalForm());
            } else {
                this.pieceImage =
                        new Image(Objects.requireNonNull(this.getClass().getResource(WHITE_PIECE_PATH)).toExternalForm());
            }

        }
        if (pieceType == PieceType.DIAGONAL) {
            this.flip();
        }
        this.setImage(pieceImage);
    }


    public void flip() {
        this.setRotate(this.getRotate() + 45);
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Image getPieceImage() {
        return pieceImage;
    }

    public void setPieceImage(Image pieceImage) {
        this.pieceImage = pieceImage;
    }
}
