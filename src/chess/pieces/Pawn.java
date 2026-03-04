package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.enums.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            // special move en passant white
            if (position.getRow() == 3) {
                Position toLeft = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(toLeft) && isThereOpponentPiece(toLeft) && getBoard().piece(toLeft) == chessMatch.getEnPassantVulnerable()) {
                    matrix[toLeft.getRow() - 1][toLeft.getColumn()] = true;
                }

                Position toRight = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(toRight) && isThereOpponentPiece(toRight) && getBoard().piece(toRight) == chessMatch.getEnPassantVulnerable()) {
                    matrix[toRight.getRow() - 1][toRight.getColumn()] = true;
                }
            }
        } else {
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }

            // special move en passant black
            if (position.getRow() == 4) {
                Position toLeft = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(toLeft) && isThereOpponentPiece(toLeft) && getBoard().piece(toLeft) == chessMatch.getEnPassantVulnerable()) {
                    matrix[toLeft.getRow() + 1][toLeft.getColumn()] = true;
                }

                Position toRight = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(toRight) && isThereOpponentPiece(toRight) && getBoard().piece(toRight) == chessMatch.getEnPassantVulnerable()) {
                    matrix[toRight.getRow() + 1][toRight.getColumn()] = true;
                }
            }
        }

        return matrix;
    }
}