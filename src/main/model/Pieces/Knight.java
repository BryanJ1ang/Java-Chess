package model.Pieces;

// Represents knight piece
public class Knight extends Piece {
    private final String type = "Knight";

    // REQUIRES: Colour is one of: "White" "Black"
    // EFFECTS: Constructor for knight
    public Knight(String colour) {
        super(colour);
    }

    // EFFECTS: Constructor for knight
    public Knight(Boolean colour) {
        super(colour);
    }

    // REQUIRES: parameters are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int x, int y, int nextx, int nexty) {
        if (nextx < 0 || nextx > 7 || nexty < 0 || nexty > 7) {
            return false;
        }
        return (((nexty == y + 2) || (nexty == y - 2)) && ((nextx == x + 1) || (nextx == x - 1)))
                || (((nexty == y + 1) || (nexty == y - 1)) && ((nextx == x + 2) || (nextx == x - 2)));
    }

    public String getType() {
        return type;
    }
}
