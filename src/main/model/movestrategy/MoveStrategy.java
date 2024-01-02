package model.movestrategy;
import javatuples.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

// Implementation for Strategy Pattern

public interface MoveStrategy {

    public void legalMoves(Game game, Piece piece, List<Triplet<Piece, Integer, Integer>> list);


    public Boolean canMove(Game game, Piece b, int nextX, int nextY);
}
