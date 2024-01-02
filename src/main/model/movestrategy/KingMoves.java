package model.movestrategy;


import javatuples.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

public class KingMoves implements MoveStrategy {
    public void legalMoves(Game game, Piece king, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = king.getXposition();
        int posY = king.getYposition();
        for (int x = posX - 1; x < posX + 2; x++) {
            for (int y = posY - 1; y < posY + 2; y++) {
                if (x == posX && y == posY) {
                    continue;
                } else if (game.validMove(king, x, y)) {
                    list.add(new Triplet<>((Piece) king, x, y));
                }
            }
        }
    }

    @Override
    public Boolean canMove(Game game, Piece p, int nextX, int nextY) {
        return  p.canMove(p.getXposition(), p.getYposition(), nextX, nextY);
    }

}
