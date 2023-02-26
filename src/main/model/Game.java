package model;

import java.util.Timer;


// import timer
// board here
// game start Boolean
//
// creates new players
public class Game {
    private final Board bd = new Board();
    private Player player1 = new Player(true); //white
    private Player player2 = new Player(false); //black
    private Boolean player1turn = true;
    private Boolean player2turn = false;
    private Boolean gamestatus = true;



    public  Game(String str) {
        if (str == "default") {
            defaultSetUp();
        }
    }

    public Boolean isPlayersTurn(Player player) {
        if ((player == player1 && player1turn)
                || (player == player2 && player2turn)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void movePiece(Piece p,int nextx, int nexty) {
        bd.movePiece(p, nextx, nexty);
        player1turn = ! player1turn;
        player2turn = ! player2turn;
    }


    public Boolean doesPieceBelongToPlayer(Player player, Piece p) {
        return player.getWhite() == p.getWhite();
    }


    public Boolean isPieceTurnToMove(Piece p) {
        if (p.white && player1turn) {
            return true;
        }
        if (!p.white && player2turn) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean canBeMovedThere(Piece piece, int nextx, int nexty) {
        return bd.validMove(piece, nextx, nexty);
    }

    public void addPiece(Piece p, int x, int y) {
        bd.addPiece(p, x, y);
        if (p.white) {
            player1.addPiece(p);
        } else {
            player2.addPiece(p);
        }
    }

    public void defaultSetUp() {
        addWhitePawns();
        addBlackPawns();
        addDefaultBackRowBlack();
        addDefaultBackRowWhite();
    }

    private void addWhitePawns() {
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        Pawn p3 = new Pawn("White");
        Pawn p4 = new Pawn("White");
        Pawn p5 = new Pawn("White");
        Pawn p6 = new Pawn("White");
        Pawn p7 = new Pawn("White");
        Pawn p8 = new Pawn("White");
        addPiece(p1,0, 6 );
        addPiece(p2,1, 6 );
        addPiece(p3,2, 6 );
        addPiece(p4,3, 6 );
        addPiece(p5,4, 6 );
        addPiece(p6,5, 6 );
        addPiece(p7,6, 6 );
        addPiece(p8,7, 6 );
    }

    private void addBlackPawns() {
        Pawn p9 = new Pawn("Black");
        Pawn p10 = new Pawn("Black");
        Pawn p11 = new Pawn("Black");
        Pawn p12 = new Pawn("Black");
        Pawn p13 = new Pawn("Black");
        Pawn p14 = new Pawn("black");
        Pawn p15 = new Pawn("Black");
        Pawn p16 = new Pawn("Black");
        addPiece(p9,0, 1 );
        addPiece(p10,1, 1 );
        addPiece(p11,2, 1 );
        addPiece(p12,3, 1 );
        addPiece(p13,4, 1 );
        addPiece(p14,5, 1 );
        addPiece(p15,6, 1 );
        addPiece(p16,7, 1 );
    }

    private void addDefaultBackRowWhite() {
        Bishop b1 = new Bishop("White");
        Bishop b2 = new Bishop("White");
        Knight kn1 = new Knight("White");
        Knight kn2 = new Knight("White");
        Rook r1 = new Rook("White");
        Rook r2 = new Rook("White");
        King k1 = new King("White");
        Queen q1 = new Queen("White");
        addPiece(kn1, 1, 7);
        addPiece(kn2, 6, 7);
        addPiece(b1, 2, 7);
        addPiece(b2, 5, 7);
        addPiece(r1, 0,7);
        addPiece(r2, 7,7);
        addPiece(q1, 3, 7);
        addPiece(k1, 4, 7);
    }

    private void addDefaultBackRowBlack() {
        King k2 = new King("Black");
        Queen q2 = new Queen("Black");
        Rook r3 = new Rook("Black");
        Rook r4 = new Rook("Black");
        Bishop b3 = new Bishop("Black");
        Bishop b4 = new Bishop("Black");
        Knight kn3 = new Knight("Black");
        Knight kn4 = new Knight("Black");
        addPiece(kn3, 1, 0);
        addPiece(kn4, 6, 0);
        addPiece(b3, 2, 0);
        addPiece(b4, 5, 0);
        addPiece(r3, 0,0);
        addPiece(r4, 7,0);
        addPiece(q2, 3, 0);
        addPiece(k2, 4, 0);
    }

    private void defaultPiecesWhite() {
        King k1 = new King("White");
        Queen q1 = new Queen("White");
        Rook r1 = new Rook("White");
        Rook r2 = new Rook("White");
        Bishop b1 = new Bishop("White");
        Bishop b2 = new Bishop("White");
        Knight kn1 = new Knight("White");
        Knight kn2 = new Knight("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        Pawn p3 = new Pawn("White");
        Pawn p4 = new Pawn("White");
        Pawn p5 = new Pawn("White");
        Pawn p6 = new Pawn("White");
        Pawn p7 = new Pawn("White");
        Pawn p8 = new Pawn("White");
    }

    private void defaultPiecesBlack() {
        King k2 = new King("Black");
        Queen q2 = new Queen("Black");
        Rook r3 = new Rook("Black");
        Rook r4 = new Rook("Black");
        Bishop b3 = new Bishop("Black");
        Bishop b4 = new Bishop("Black");
        Knight kn3 = new Knight("Black");
        Knight kn4 = new Knight("Black");
        Pawn p9 = new Pawn("Black");
        Pawn p10 = new Pawn("Black");
        Pawn p11 = new Pawn("Black");
        Pawn p12 = new Pawn("Black");
        Pawn p13 = new Pawn("Black");
        Pawn p14 = new Pawn("black");
        Pawn p15 = new Pawn("Black");
        Pawn p16 = new Pawn("Black");
    }

    public void gameOver() {
        gamestatus = false;
    }
    public Board getBd() {
        return bd;
    }

    public Boolean getPlayer1turn() {
        return player1turn;
    }

    public Boolean getPlayer2turn() {
        return player2turn;
    }

    public Boolean getGamestatus() {
        return gamestatus;
    }
 }


