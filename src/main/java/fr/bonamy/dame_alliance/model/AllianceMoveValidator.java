package fr.bonamy.dame_alliance.model;

import fr.bonamy.dame_alliance.util.Point;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AllianceMoveValidator implements MoveValidator {

    private static final Point N = new Point(0, -1);
    private static final Point S = new Point(0, 1);
    private static final Point E = new Point(1, 0);
    private static final Point W = new Point(-1, 0);
    private static final Point NE = new Point(1, -1);
    private static final Point NW = new Point(-1, -1);
    private static final Point SE = new Point(1, 1);
    private static final Point SW = new Point(-1, 1);

    private static final Set<Point> MOVES_DIAGONAL_WHITE = java.util.Set.of(SE, SW);
    private static final Set<Point> MOVES_ORTHOGONAL_WHITE = java.util.Set.of(S, E, W);
    private static final Set<Point> MOVES_DIAGONAL_BLACK = java.util.Set.of(NE, NW);
    private static final Set<Point> MOVES_ORTHOGONAL_BLACK = java.util.Set.of(N, W, E);
    private static final Set<Point> MOVES_KING = java.util.Set.of(NE, NW, SE, SW, N, S, E, W);

    @Override
    public boolean isMoveValid(Piece piece, Point from, Point to, Map<Point, Piece> boardState) {
        Set<Point> validMoves = getValidMoves(piece, from, boardState);
        return validMoves.contains(to);
    }

    @Override
    public Set<Point> getValidMoves(Piece piece, Point position, Map<Point, Piece> boardState) {
        Set<Point> moves = (piece.getType() == PieceType.KING)
                ? MOVES_KING
                : piece.getColor() == PieceColor.WHITE
                ? (piece.getType() == PieceType.DIAGONAL ? MOVES_ORTHOGONAL_WHITE : MOVES_DIAGONAL_WHITE)
                : (piece.getType() == PieceType.DIAGONAL ? MOVES_ORTHOGONAL_BLACK : MOVES_DIAGONAL_BLACK);
        Set<Point> moveSet = moves.stream()
                .map(position::add)
                .filter(newPosition -> isWithinBounds(newPosition) && !boardState.containsKey(newPosition))
                .collect(Collectors.toSet());
        Set<Point> captureMoves = getCaptureMoves(piece, position, boardState);
        moveSet.addAll(captureMoves);
        return moveSet;
    }

    @Override
    public Set<Point> getCaptureMoves(Piece piece, Point position, Map<Point, Piece> boardState) {
        Set<Point> moves = (piece.getType() == PieceType.KING)
                ? MOVES_KING
                : piece.getColor() == PieceColor.WHITE
                ? (piece.getType() == PieceType.DIAGONAL ? MOVES_DIAGONAL_WHITE : MOVES_ORTHOGONAL_WHITE)
                : (piece.getType() == PieceType.DIAGONAL ? MOVES_DIAGONAL_BLACK : MOVES_ORTHOGONAL_BLACK);
        Set<Point> captureMoves = new HashSet<>();
        for (Point move : moves) {
            Point intermediate = position.add(move);
            Point target = intermediate.add(move);

            if (isWithinBounds(target)
                    && boardState.containsKey(intermediate) // Il y a une pièce ennemie
                    && boardState.get(intermediate).getColor() != piece.getColor()
                    && !boardState.containsKey(target)) { // La case d'atterrissage est libre

                captureMoves.add(target);
            }
        }
        return captureMoves;
    }

    @Override
    public void capture(Piece piece, Point from, Point to, Map<Point, Piece> boardState) {
        Point direction = to.sub(from);
        Point capturedPosition = from.add(new Point(direction.getA() / 2, direction.getB() / 2));
        Piece capturedPiece = boardState.remove(capturedPosition);
        if (capturedPiece != null) {
            capturedPiece.setActive(false);
        }
    }

    @Override
    public boolean canBePromoted(Piece piece, Point to) {
        int lastRow = (piece.getColor() == PieceColor.WHITE)
                ? GameConfig.getBoardSize() - 1
                : 0;

        return to.getB() == lastRow;
    }

    private boolean isWithinBounds(Point position) {
        int size = GameConfig.getBoardSize();
        return position.getA() >= 0 && position.getA() < size && position.getB() >= 0 && position.getB() < size;
    }
}