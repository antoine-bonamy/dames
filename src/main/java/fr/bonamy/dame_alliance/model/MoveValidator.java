package fr.bonamy.dame_alliance.model;

import fr.bonamy.dame_alliance.util.Point;

import java.util.Map;
import java.util.Set;

public interface MoveValidator {
    boolean isMoveValid(Piece piece, Point from, Point to, Map<Point, Piece> boardState);
    Set<Point> getValidMoves(Piece piece, Point position, Map<Point, Piece> boardState);
    Set<Point> getCaptureMoves(Piece piece, Point position, Map<Point, Piece> boardState);
    boolean canBePromoted(Piece piece, Point to);
    void capture(Piece piece, Point from, Point to, Map<Point, Piece> boardState);
}