package model.movestrategy;

import javatuples.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

public class KnightMoves implements MoveStrategy {
    public void legalMoves(Game game, Piece knight, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = knight.getXposition();
        int posY = knight.getYposition();
        if (game.validMove(knight, posX + 2, posY + 1)) {
            list.add(new Triplet<>((Piece) knight, posX + 2, posY + 1));
        }
        if (game.validMove(knight, posX + 2, posY - 1)) {
            list.add(new Triplet<>((Piece) knight, posX + 2, posY - 1));
        }
        if (game.validMove(knight, posX - 2, posY + 1)) {
            list.add(new Triplet<>((Piece) knight, posX - 2, posY + 1));
        }
        if (game.validMove(knight, posX - 2, posY - 1)) {
            list.add(new Triplet<>((Piece) knight, posX - 2, posY - 1));
        }
        if (game.validMove(knight, posX + 1, posY + 2)) {
            list.add(new Triplet<>((Piece) knight, posX + 1, posY + 2));
        }
        if (game.validMove(knight, posX + 1, posY - 2)) {
            list.add(new Triplet<>((Piece) knight, posX + 1, posY - 2));
        }
        if (game.validMove(knight, posX - 1, posY + 2)) {
            list.add(new Triplet<>((Piece) knight, posX - 1, posY + 2));
        }
        if (game.validMove(knight, posX - 1, posY - 2)) {
            list.add(new Triplet<>((Piece) knight, posX - 1, posY - 2));
        }
    }

    @Override
    public Boolean canMove(Game game, Piece b, int nextX, int nextY) {
        Piece piece = game.getBd().getPiece(nextX, nextY);
        if (piece != null && piece.isWhite() == b.isWhite()) {
            return false;
        }
        return b.canMove(b.getXposition(), b.getYposition(), nextX, nextY);
    }
}
