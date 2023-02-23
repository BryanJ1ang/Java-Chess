package model;


import static java.lang.Math.abs;

public class Board {
    private Piece[][] bd = new Piece[7][7];
    // FIRST DIMENSION = COLUMNS
    // SECOND DIMENSION = ROWS

    // REQUIRES: Piece is one of (King, Queen, Rook, Bishop, Knight, Pawn)
    //           x and y are values in between [0,7] inclusive
    // METHOD: this, Piece
    // EFFECT: Adds piece to location on board
    public void addPiece(Piece piece, int x, int y) {
        bd[x][y] = piece;
        piece.setPositions(x, y);
    }

    // MODIFIES: this, Piece
    // EFFECT: Moves a piece to given location. Captures if position already occupied
    public movePiece(Piece p, int nextx, int nexty) {
        bd[nextx][nexty] = p;
        this.removePiece(p.xposition,p.yposition);
        p.setPositions(nextx, nexty);
    }


    // REQUIRE: X and Y are between 0 and 7
    // EFFECT: gets piece from given a square
    public Piece getPiece(int x, int y) {
        return bd[x][y];
    }

    // METHOD: this
    // EFFECT: removes a piece from a given position
    public void removePiece(int x, int y) {
        bd[x][y] = null;
    }


    //EFFECT: determines if a move is valid. Piece can move in such direction
    public Boolean validMove(Piece p, int nextx, int nexty) {
        Boolean b = false;
        if (this.getPiece(nextx, nexty) != null && p.white == this.getPiece(nextx, nexty).white) {
            return false;
        }
        if (p instanceof Bishop) {
            b = canMoveBishop(p, nextx, nexty);
        }
        if (p instanceof Queen) {
            b = canMoveQueen(p, nextx, nexty);
        }
        if (p instanceof Knight) {
            b = canMoveKnight(p, nextx, nexty);
        }
        if (p instanceof Rook) {
            b = canMoveRook(p, nextx, nexty);
        }
        if (p instanceof Pawn) {
            b = canMovePawn(p, nextx, nexty);
        }
        if (p instanceof King) {
            b = p.canMove(p.xposition, p.yposition, nextx, nexty);
        }
        return b;
    }

    //EFFECTS: if given bishop can move to next position
    private Boolean canMoveBishop(Piece b, int nextx, int nexty) {
       return b.canMove(b.xposition, b.yposition, nextx, nexty)
               && visionDiagonal(b, nextx, nexty);
    }

    //EFFECTS: if given queen can move to next position
    private Boolean canMoveQueen(Piece q, int nextx, int nexty) {
        Boolean b = false;
        if (q.canMove(q.xposition, q.yposition, nextx, nexty)) {
            if ((nextx == q.xposition || nexty == q.yposition)
            && this.visionStraight(q, nextx, nexty)) {
                b = true;
            } else {
                if (this.visionDiagonal(q, nextx, nexty)) {
                    b = true;
                }
            }
        }
        return b;
    }

    //EFFECTS: if given rook can move to next position
    private Boolean canMoveRook(Piece r, int nextx, int nexty) {
        return r.canMove(r.xposition, r.yposition, nextx, nexty)
                && visionStraight(r, nextx, nexty);
    }

    //EFFECTS: if given knight can move to next position
    private Boolean canMoveKnight(Piece k, int nextx, int nexty) {
        return k.canMove(k.xposition, k.yposition, nextx, nexty);
    }

    //EFFECTS: if given pawn can move to next position
    private Boolean canMovePawn(Piece p, int nextx, int nexty) {
        Boolean b = false;
        if (p.canMove(p.xposition, p.yposition, nextx, nexty)
                && this.getPiece(nextx, nexty) == null) {
            b = true;
        }
        if (p.white) {
            if (abs(nextx - p.xposition) == 1
                    && nexty == p.yposition - 1
            && this.getPiece(nextx, nexty) != null)
                b = true;
        }
        if (!p.white) {
            if (abs(nextx - p.xposition) == 1
                    && nexty == p.yposition + 1
                    && this.getPiece(nextx, nexty) != null)
                b = true;
        }
        return b;
    }

    // EFFECT: return true if given king is in check
    public Boolean check(King x) {
        Boolean b = false;
        int kx = x.getXposition();
        int ky = x.getYposition();
        // !x.white
        for (int xcord = 0; xcord < 7; xcord++) {
            for (int ycord = 0; ycord < 7; ycord++) {
                if (validMove(this.getPiece(xcord,ycord), kx, ky)) {
                    b = true;
                }
            }
        }
        return b;
    }

    // REQUIRES: x and y is not location of given piece
    // EFFECTS: Returns true if piece can see given square diaganolly
    public Boolean visionDiagonal(Piece p, int x, int y) {
        Boolean b = true;
        if (x > p.getXposition() && y > p.getYposition()) {
            for (int xcord = p.getXposition() + 1; xcord < x; xcord++) {
                for (int ycord = p.getYposition() + 1; ycord < y; ycord++) {
                    if ((x - xcord == y - ycord) && (this.getPiece(xcord, ycord) != null)) {
                        b = false;
                    }
                }
            }
        }
        if (x > p.getXposition() && y < p.getYposition()) {
            for (int xcord = p.getXposition() + 1; xcord < x; xcord++) {
                for (int ycord = p.getYposition() - 1; ycord > y; ycord--) {
                    if ((x - xcord == ycord - y && (this.getPiece(xcord, ycord) != null))) {
                        b = false;
                    }
                }
            }
        }
        if (x < p.getXposition() && y > p.getYposition()) {
            for (int xcord = p.getXposition() - 1; xcord > x; xcord--) {
                for (int ycord = p.getYposition() + 1; ycord < y; ycord++) {
                    if ((xcord - x == y - ycord && (this.getPiece(xcord, ycord) != null))) {
                        b = false;
                    }
                }
            }
        }
        if (x < p.getXposition() && y < p.getYposition()) {
            for (int xcord = p.getXposition() - 1; xcord > x; xcord--) {
                for (int ycord = p.getYposition() - 1; ycord > y; ycord--) {
                    b = false;
                }
            }
        }
        return b;
    }


    //EFFECTS: Returns true if piece can see given square vertically/horizontally
    public Boolean visionStraight(Piece p, int x, int y) {
        Boolean b = true;
        if (x == p.getXposition() && y > p.getYposition()) {
            for (int ycord = p.getYposition() + 1; ycord < y; ycord++) {
                if (this.getPiece(x, ycord) != null) {
                    b = false;
                }
            }
        }
        if (x == p.getXposition() && y < p.getYposition()) {
            for (int ycord = p.getYposition() - 1; y < ycord; ycord--) {
                if (this.getPiece(x, ycord) != null) {
                    b = false;
                }
            }
        }
        if (y == p.getYposition() && x > p.getXposition()) {
            for (int xcord = p.getXposition() + 1; xcord < x; xcord++) {
                if (this.getPiece(xcord, y) != null) {
                    b = false;
                }
            }
        }
        if (y == p.getYposition() && x < p.getXposition()) {
            for (int xcord = p.getXposition() - 1; xcord > x; xcord--) {
                if (this.getPiece(xcord, y) != null) {
                    b = false;
                }
            }
        }
        return b;
    }
}


