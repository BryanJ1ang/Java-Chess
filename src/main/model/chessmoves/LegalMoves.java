package model.chessmoves;
import Tuple.Triplet;
import model.Game;
import model.pieces.Piece;

import java.util.List;

// Implementation for Strategy Pattern

public interface LegalMoves {

    public void legalMoves(Game game, Piece king, List<Triplet<Piece, Integer, Integer>> list);

}
