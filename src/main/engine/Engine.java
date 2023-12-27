package engine;

import Tuple.Triplet;
import model.Game;
import model.Pieces.*;

import java.util.List;

public class Engine {
    // 39
    int miniMax;
    int depth;


    public int miniMax(Game game, ) {

    }

    // EFFECTS: Returns smallest value
    public int mini(Game game, int depth, ) {

    }

    // EFFECTS: Returns largest value
    public int max() {

        mini()
    }

    // EFFECTS: Returns a list of valid moves for current player's turn
    public List<Triplet<Piece, Integer, Integer>> validMoves(Game game) {

        return null;
    }


    // EFFECTS: Evaluates the balance between White and Black in current game state
    //          More positive = white Greater advantage
    //          More negative = Black greater advantage
    //          Zero = Both sides balanced
    public int evaluateGameState(Game game) {
        int minimax = 0;
        for (Piece piece :game.getPlayer1().getPieces()) {
            minimax += pieceToValue(piece);
        }
        for (Piece piece :game.getPlayer2().getPieces()) {
            minimax -= pieceToValue(piece);
        }
        return minimax;
    }


    // EFFECTS: Converts a piece to its relative value
    public int pieceToValue(Piece piece) {
        if (piece instanceof King) {
            return 0;
        } else if (piece instanceof Queen) {
            return 9;
        } else if (piece instanceof Bishop) {
            return 3;
        } else if (piece instanceof Knight) {
            return 3;
        } else if (piece instanceof Rook) {
            return 5;
        } else {
            return 1;
        }
    }
}
