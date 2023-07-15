package model;

// Represents rook piece
public class Rook extends Piece {
    private final String type = "Rook";

    // EFFECTS: Constructor for Rook
    public Rook(String colour) {
        super(colour);
    }

    // EFFECTS: Constructor for Rook
    public Rook(Boolean colour) {
        super(colour);
    }

    // REQUIRES: parameters are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        return ((currentx == nextx) || (currenty == nexty));
    }

    public String getType() {
        return type;
    }
}
