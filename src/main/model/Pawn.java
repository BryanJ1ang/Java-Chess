package model;

// Represents pawn piece
public class Pawn extends Piece {
    private final String type = "Pawn";
    private Boolean enPassant = false;

    // REQUIRES: Colour is one of: "White" "Black"
    // EFFECTS: Constructor for pawn


    public Pawn(String colour) {
        super(colour);
    }

    public Pawn(Boolean colour) {
        super(colour);
    }

    // REQUIRES: parameters are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        if (white == false) {
            if (((currenty == 1))) {
                moved = true;
                return ((currentx == nextx) && ((nexty == currenty + 1) || (nexty == currenty + 2)));
            } else {
                return (currentx == nextx) && ((nexty == currenty + 1));
            }

        } else {
            if (((currenty == 6))) {
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

    public Boolean getEnPassant() {
        return enPassant;
    }

}
