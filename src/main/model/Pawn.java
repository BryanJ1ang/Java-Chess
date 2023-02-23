package model;

public class Pawn extends Piece {

    public Pawn(String x) {
        moved = false;
        if (x == "White") {
            white = true;
        } else {
            white = false;
        }

    }


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
}
