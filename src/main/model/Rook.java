package model;

public class Rook extends Piece {
    private final String type = "Rook";

    // REQUIRES: Colour is one of: "White" "Black"
    // EFFECTS: Constructor for Rook
    public Rook(String colour) {
        if (colour == "White") {
            white = true;
        }
        if (colour == "Black") {
            white = false;
        }
    }

    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        return ((currentx == nextx) || (currenty == nexty));
    }
    public String getType() {
        return type;
    }
}
