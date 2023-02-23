package model;

public class King extends Piece {

    // REQUIRES: colour is one of: "White" "Black"
    // EFFECT: constructor for king at given location
    public  King(String colour) {
        moved = false;
        if (colour == "White") {
            white = true;
        }
        if (colour == "Black") {
            white = false;
        }

    }

    // REQUIRES: current x, currenty, nextx, nexty are all values between [0,7] inclusive
// EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        return ((nextx == currentx + 1) || (nextx == currentx - 1) || (nextx == currentx)) && ((nexty == currenty) ||
                (nexty == currenty + 1) || (nexty == currenty - 1));
    }
}
