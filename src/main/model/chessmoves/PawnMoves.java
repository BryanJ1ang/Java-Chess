package model.chessmoves;

import Tuple.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

public class PawnMoves implements LegalMoves{

    @Override
    public void legalMoves(Game game, Piece pawn, List<Triplet<Piece, Integer, Integer>> list) {
        if (pawn.getWhite()) {
            if (game.getBd().getPiece(pawn.getXposition(), pawn.getYposition() - 1) == null) {
                list.add(new Triplet<>(pawn, pawn.getXposition(), pawn.getYposition() - 1));
                if (pawn.getYposition() == 6 && game.getBd().getPiece(pawn.getXposition(), 4) == null) {
                    list.add(new Triplet<>(pawn, pawn.getXposition(), 4));
                }
            }
            } else {
            if (game.getBd().getPiece(pawn.getXposition(), pawn.getYposition() + 1) == null) {
                list.add(new Triplet<>(pawn, pawn.getXposition(), pawn.getYposition() + 3));
                if (pawn.getYposition() == 1 && game.getBd().getPiece(pawn.getXposition(), 3) == null) {
                    list.add(new Triplet<>(pawn, pawn.getXposition(), 3));
                }
            }
        }
    }
}
