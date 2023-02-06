package model;


import static java.lang.Math.abs;

public class Board {
    public Piece[][] Board = new Piece[7][7];
    // FIRST DIMENSION = COLUMNS
    // SECOND DIMENSION = ROWS

    //EFFECT: Constructor with either empty or
    //        default setup of piece
    public void Board(String str) {

    }

    // EFFECT: Moves/captures piece if it is a valid move
    // if validmove?
    // if not empty next x next y then capture
    // not? move it to next coordinates
    public void movePiece(int x, int y,int nextx, int nexty) {

    }

    // REQUIRE: X and Y are between 0 and 7
    // EFFECT: gets piece from given a square
    private Piece getPiece(int x, int y){
       return Board[x][y];
    }

    private void removePiece(int x, int y){
        Board[x][y] = null;
    }

    //EFFECT: determines if a move is valid. Piece can move in such direction and
    // king is not checked after move.
    private Boolean validMove(int x, int y, int nextx, int nexty){
    return false; // stub
    }

    // METHOD: this
    // EFFECT: removes a piece from a given position
    public static void capturePiece() {

    }
}


 class King implements Piece {
    int x;
    int y;
    Boolean white;

    // EFFECT: constructor for king at given location
    public void placeKing(int x, int y){

    }

    // REQUIRES: current x, currenty, nextx, nexty are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty){
        return ((nextx == currentx + 1) || (nextx == currentx - 1) || (nextx == currentx)) && ((nexty == currenty) ||
                (nexty == currenty + 1) || (nexty == currenty - 1));
    }
}

 class Queen implements Piece {
    Boolean white;

     // REQUIRES: current x, currenty, nextx, nexty are all values between [0,7] inclusive
     // EFFECT: return true if piece can move to specified position
    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
    return (abs(currentx - nextx) == abs(currenty - nexty)) || ((currentx == nextx) || (currenty == nexty));
    }
}

    // REQUIRES: current x, currenty, nextx, nexty are all values between [0,7] inclusive
    // EFFECT: return true if piece can move to specified position

 class Rook implements Piece {
    Boolean white;

    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        return ((currentx == nextx) || (currenty == nexty));
    }
}

 class Knight implements Piece {
    Boolean white;
    @Override
    public Boolean canMove(int x, int y, int nextx, int nexty) {
        return null;
    }
}

 class Bishop implements Piece {
    Boolean white;

    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {

        return (abs(currentx - nextx) == abs(currenty - nexty));
    }
}

 class Pawn implements Piece {
    Boolean white;
    Boolean moved;


     public Pawn(String x) {
         if (x == "White"){
             white = true;
             moved = false;
         }
         else {
             white = false;
             moved = false;
         }

     }


    public Boolean canMove(int currentx, int currenty, int nextx, int nexty) {
        if (white == false) {
            if ((moved == false) && (currenty == 1)) {
                moved = true;
                return ((currentx == nextx) && ((nexty == currenty + 1) || (nexty == currenty + 2)));
            }
            else {
                return (currentx == nextx) && ((nexty == currenty + 1));
            }

        }

        else {
            if ((moved == false) && (currenty == 6)) {
                moved = true;
                return (currentx == nextx) && ((nexty == currenty - 1) || (nexty == currenty - 2));
            }
            else {
                return (currentx == nextx) && ((nexty == currenty - 1));
            }
        }
    }

    public Boolean getWhite(){
         return white;
    }
 }

