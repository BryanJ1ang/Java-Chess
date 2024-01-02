package model.movestrategy;

import javatuples.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

import static java.lang.Math.abs;

public class PawnMoves implements MoveStrategy {

    @Override
    public void legalMoves(Game game, Piece pawn, List<Triplet<Piece, Integer, Integer>> list) {
        if (pawn.isWhite()) {
            if (game.getBd().getPiece(pawn.getXposition(), pawn.getYposition() - 1) == null) {
                list.add(new Triplet<>(pawn, pawn.getXposition(), pawn.getYposition() - 1));
                if (pawn.getYposition() == 6 && game.getBd().getPiece(pawn.getXposition(), 4) == null) {
                    list.add(new Triplet<>(pawn, pawn.getXposition(), 4));
                }
            }
            } else {
            if (game.getBd().getPiece(pawn.getXposition(), pawn.getYposition() + 1) == null) {
                list.add(new Triplet<>(pawn, pawn.getXposition(), pawn.getYposition() + 1));
                if (pawn.getYposition() == 1 && game.getBd().getPiece(pawn.getXposition(), 3) == null) {
                    list.add(new Triplet<>(pawn, pawn.getXposition(), 3));
                }
            }
        }
    }

    @Override
    public Boolean canMove(Game game, Piece p, int nextX, int nextY) {
        Boolean b = false;
        if (p.canMove(p.getXposition(), p.getYposition(), nextX, nextY)
                && game.getBd().getPiece(nextX, nextY) == null) {
            b = true;
        }
        if (p.isWhite()) {
            if (abs(nextX - p.getXposition()) == 1
                    && nextY == p.getYposition() - 1
                    && game.getBd().getPiece(nextX, nextY) != null) {
                b = true;
            }
        }
        if (!p.isWhite()) {
            if (abs(nextX - p.getXposition()) == 1
                    && nextY == p.getYposition() + 1
                    && game.getBd().getPiece(nextX, nextY) != null) {
                b = true;
            }
        }
        return b;    }
}
