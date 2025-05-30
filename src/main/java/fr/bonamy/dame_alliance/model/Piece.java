package fr.bonamy.dame_alliance.model;

import java.util.Objects;

public class Piece {

    private PieceColor color;
    private PieceType type;
    private boolean isActive;

    public Piece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
        this.isActive = true;
    }

    public Piece(PieceColor color, PieceType type, boolean isActive) {
        this.color = color;
        this.type = type;
        this.isActive = isActive;
    }

    public void makeKing() {
        this.type = PieceType.KING;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                ", isActive=" + isActive +
                '}';
    }
}
