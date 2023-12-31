package model.chessmoves;

import Tuple.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

public class BishopMoves implements LegalMoves {


    // REQUIRES: piece is actual type Queen or Bishop
    // EFFECTS: Adds legal diagonal moves to list
    public void diagonalMoves(Game game, Piece piece, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = piece.getXposition();
        int posY = piece.getYposition();
        int y = posY;
        for (int x = posX + 1; x < 8; x++) {
            y += 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>(piece, x, y));
            } else {
                break;
            }
        }
        y = posY;
        for (int x = posX + 1; x < 8; x++) {
            y -= 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>(piece, x, y));
            } else {
                break;
            }
        }
        y = posY;
        for (int x = posX - 1; x > -1; x--) {
            y += 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>(piece, x, y));
            } else {
                break;
            }
        }
        y = posY;
        for (int x = posX - 1; x > -1; x--) {
            y -= 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>(piece, x, y));
            } else {
                break;
            }
        }
    }

    @Override
    public void legalMoves(Game game, Piece king, List<Triplet<Piece, Integer, Integer>> list) {
        diagonalMoves(game, king, list);
    }
}
