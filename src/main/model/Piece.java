package model;

// Abstract class for the pieces in chess
public abstract class Piece {
    int xposition;
    int yposition;
    protected Boolean white;
    protected Boolean moved = false;


    // EFFECT: true if piece can move to given location
    public abstract Boolean canMove(int currentx, int curruenty, int x, int y);

    // MODIFIES: this
    // EFFECT: sets current coordinates for piece
    public void setPositions(int x, int y) {
        xposition = x;
        yposition = y;
    }

    public abstract String getType();

    public Boolean getWhite() {
        return white;
    }

    public Boolean getMoved() {
        return moved;
    }

    public int getXposition() {
        return xposition;
    }

    public int getYposition() {
        return yposition;
    }

}

