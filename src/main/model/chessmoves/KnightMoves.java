package model.chessmoves;

import Tuple.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

public class KnightMoves implements LegalMoves{
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
}
