package model;

// Represents pawn piece
public class Pawn extends Piece {
    private final String type = "Pawn";

    // REQUIRES: Colour is one of: "White" "Black"
    // EFFECTS: Constructor for pawn
    public Pawn(String x) {
        moved = false;
        white = (x.equals("White"));
    }


    // REQUIRES: parameters are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        if (white == false) {
            if ((moved == false) && (currenty == 1)) {
                moved = true;
                return ((currentx == nextx) && ((nexty == currenty + 1) || (nexty == currenty + 2)));
            } else {
                return (currentx == nextx) && ((nexty == currenty + 1));
            }

        } else {
            if ((moved == false) && (currenty == 6)) {
                moved = true;
                return (currentx == nextx) && ((nexty == currenty - 1) || (nexty == currenty - 2));
            } else {
                return (currentx == nextx) && ((nexty == currenty - 1));
            }
        }
    }

    public String getType() {
        return type;
    }
}
