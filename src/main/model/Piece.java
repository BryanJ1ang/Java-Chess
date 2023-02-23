package model;

public abstract class Piece {
    int xposition;
    int yposition;
    protected Boolean white;
    protected Boolean moved;

    public abstract Boolean canMove(int currentx, int curruenty, int x, int y);

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

    public void setPositions(int x, int y) {
        xposition = x;
        yposition = y;
    }
}