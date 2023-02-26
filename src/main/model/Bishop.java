package model;

import static java.lang.Math.abs;

public class Bishop extends Piece {
    private final String type = "Bishop";

    //REQUIRES: Colour is one of: "White" "Black"
    // EFFECT: Constructor for bishop
    public Bishop(String colour) {
        if (colour == "White") {
            white = true;
        }
        if (colour == "Black") {
            white = false;
        }
    }

    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {

        return (abs(currentx - nextx) == abs(currenty - nexty));
    }

    public String getType() {
        return type;
    }
}
