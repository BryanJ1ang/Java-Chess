package model;

// REQUIRES: current x, currenty, nextx, nexty are all values between [0,7] inclusive
// EFFECT: return true if piece can move to specified position
public class Knight extends Piece {
    private final String type = "Knight";

    // REQUIRES: Colour is one of: "White" "Black"
    // EFFECTS: Constructor for knight
    public Knight(String colour) {
        String type = "Knight";
        if (colour == "White") {
            white = true;
            moved = false;
        }
        if (colour == "Black") {
            white = false;
            moved = false;
        }
    }

    public Boolean canMove(int x, int y, int nextx, int nexty) {
        return (((nexty == y + 2) || (nexty == y - 2)) && ((nextx == x + 1) || (nextx == x - 1)))
                || (((nexty == y + 1) || (nexty == y - 1)) && ((nextx == x + 2) || (nextx == x - 2)));
    }
    public String getType() {
        return type;
    }
}
