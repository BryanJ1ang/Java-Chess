package model.pieces;

import model.chessmoves.KingMoves;

// Represents king piece
public class King extends Piece {
    private final String type = "King";

    // EFFECTS: Constructor for king
    public King(String colour) {
        super(colour);
        legalmoves = new KingMoves();
    }

    // EFFECTS: Constructor for king
    public King(Boolean colour) {
        super(colour);
    }

    // REQUIRES: parameters are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        if (nextx < 0 || nextx > 7 || nexty < 0 || nexty > 7) {
            return false;
        }
        return ((nextx == currentx + 1) || (nextx == currentx - 1) || (nextx == currentx)) && ((nexty == currenty)
                || (nexty == currenty + 1) || (nexty == currenty - 1));
    }

    public String getType() {
        return type;
    }

}
