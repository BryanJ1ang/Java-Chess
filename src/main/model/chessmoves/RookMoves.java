package model.chessmoves;

import Tuple.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

public class RookMoves implements LegalMoves{


    // REQUIRES: Piece is actual type Queen or Rook
    // EFFECTS: Adds legal up/down/left/right moves to list
    public void perpendicularMoves(Game game, Piece piece, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = piece.getXposition();
        int posY = piece.getYposition();
        for (int x = posX; x < 8; x++) {
            if (game.validMove(piece, x, posY)) {
                list.add(new Triplet<>((Piece) piece, x, posY));
            } else {
                break;
            }
        }
        for (int y = posY; y < 8; y++) {
            if (game.validMove(piece, posX, y)) {
                list.add(new Triplet<>((Piece) piece, posX, y));
            } else {
                break;
            }
        }
        for (int x = posX; x > -1; x--) {
            if (game.validMove(piece, x, posY)) {
                list.add(new Triplet<>((Piece) piece, x, posY));
            } else {
                break;
            }
        }
        for (int y = posY; y > -1; y--) {
            if (game.validMove(piece, posX, y)) {
                list.add(new Triplet<>((Piece) piece, posX, y));
            } else {
                break;
            }
        }
    }
    @Override
    public void legalMoves(Game game, Piece king, List<Triplet<Piece, Integer, Integer>> list) {
        perpendicularMoves(game, king, list);
    }
}
