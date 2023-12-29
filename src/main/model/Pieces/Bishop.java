package model.Pieces;

import static java.lang.Math.abs;

// represents bishop piece
public class Bishop extends Piece {
    private final String type = "Bishop";

    // EFFECTS: Constructor for Bishop
    public Bishop(String colour) {
        super(colour);
    }

    // EFFECTS: Constructor for Bishop
    public Bishop(Boolean colour) {
        super(colour);
    }

    // REQUIRES: Colour is one of: "White" "Black"
    // EFFECT: Constructor for bishop


    // REQUIRES: parameters are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        if (nextx < 0 || nextx > 7 || nexty < 0 || nexty > 7) {
            return false;
        }
        return (abs(currentx - nextx) == abs(currenty - nexty));
    }

    public String getType() {
        return type;
    }
}
