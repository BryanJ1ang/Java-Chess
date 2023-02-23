package model;

import static java.lang.Math.abs;

public class Queen extends Piece {

    // REQUIRES: Color is one of: "White" "Black"
    // EFFECTS: Constructor for Queen
    public Queen(String colour) {
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
        return (abs(currentx - nextx) == abs(currenty - nexty)) || ((currentx == nextx) || (currenty == nexty));
    }
}
