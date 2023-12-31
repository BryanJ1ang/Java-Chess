package model.chessmoves;

import Tuple.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

public class QueenMoves implements LegalMoves{


    // REQUIRES: piece is actual type Queen or Bishop
    // EFFECTS: Adds legal diagonal moves to list
    public void diagonalMoves(Game game, Piece piece, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = piece.getXposition();
        int posY = piece.getYposition();
        int y = posY;
        for (int x = posX + 1; x < 8; x++) {
            y += 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>(piece,x,y));
            } else {
                break;
            }
        }
        y = posY;
        for (int x = posX + 1; x < 8; x++) {
            y -= 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>( piece,x,y));
            } else {
                break;
            }
        }
        y = posY;
        for (int x = posX - 1; x > -1; x--) {
            y += 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>( piece,x,y));
            } else {
                break;
            }
        }
        y = posY;
        for (int x = posX - 1; x > -1; x--) {
            y -= 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>( piece,x,y));
            } else {
                break;
            }
        }
    }


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

    public void legalMoves(Game game, Piece queen, List<Triplet<Piece, Integer, Integer>> list) {
        diagonalMoves(game, queen, list);
        perpendicularMoves(game, queen, list);
    }

}
