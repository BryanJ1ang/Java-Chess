package model;

import java.util.ArrayList;
import java.util.List;

// Represents a player in a game of chess
public class Player {

    private Boolean white;

    private final List<Piece> pieces = new ArrayList<Piece>();

    // EFFECTS: Constructor for Player
    public Player(Boolean color) {
        white = color;
    }

    // MODIFIES: this
    // EFFECTS: adds piece to list of pieces belonging to player
    public void addPiece(Piece p) {
        pieces.add(p);
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
