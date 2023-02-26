package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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
        } else {
            player1turn = true;
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


    private void addPiece(String type, String colour, int x, int y) {

    }

    // EFFECT: initializes 8 of each Piece per color
    private void customPiecesInitialize() {
        customBlackBishops();
        customBlackKnights();
        customBlackPawns();
        customBlackQueens();
        customBlackRooks();
        customWhiteBishops();
        customWhitePawns();
        customWhiteQueens();
        customWhiteRooks();
        customWhiteKnights();
        King k1 = new King("White");
        King k2 = new King("Black");
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

    public void placePiece() {
        int x = 0;
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

    public ArrayList<Piece> addCustomPiece(ArrayList<Piece> pieces,String colour, int x, int y) {
        addPiece(pieces.get(0), x, y);
        pieces.remove(0);
        return pieces;
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

    private ArrayList<Piece> customWhitePawns() {
        ArrayList<Piece> pawns = new ArrayList<Piece>();
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        Pawn p3 = new Pawn("White");
        Pawn p4 = new Pawn("White");
        Pawn p5 = new Pawn("White");
        Pawn p6 = new Pawn("White");
        Pawn p7 = new Pawn("White");
        Pawn p8 = new Pawn("White");
        pawns.add(p1);
        pawns.add(p2);
        pawns.add(p3);
        pawns.add(p4);
        pawns.add(p5);
        pawns.add(p6);
        pawns.add(p7);
        pawns.add(p8);
        return pawns;
    }

    private ArrayList<Piece> customBlackPawns() {
        ArrayList<Piece> pawns = new ArrayList<Piece>();
        Pawn p9 = new Pawn("Black");
        Pawn p10 = new Pawn("Black");
        Pawn p11 = new Pawn("Black");
        Pawn p12 = new Pawn("Black");
        Pawn p13 = new Pawn("Black");
        Pawn p14 = new Pawn("black");
        Pawn p15 = new Pawn("Black");
        Pawn p16 = new Pawn("Black");
        pawns.add(p9);
        pawns.add(p10);
        pawns.add(p11);
        pawns.add(p12);
        pawns.add(p13);
        pawns.add(p14);
        pawns.add(p15);
        pawns.add(p16);
        return pawns;
    }

    private ArrayList<Piece> customWhiteQueens() {
        ArrayList<Piece> queens = new ArrayList<Piece>();
        Queen q1 = new Queen("White");
        Queen q2 = new Queen("White");
        Queen q3 = new Queen("White");
        Queen q4 = new Queen("White");
        Queen q5 = new Queen("White");
        Queen q6 = new Queen("White");
        Queen q7 = new Queen("White");
        Queen q8 = new Queen("White");
        queens.add(q1);
        queens.add(q2);
        queens.add(q3);
        queens.add(q4);
        queens.add(q5);
        queens.add(q6);
        queens.add(q7);
        queens.add(q8);
        return queens;
    }

    private ArrayList<Piece> customBlackQueens() {
        ArrayList<Piece> queens = new ArrayList<Piece>();
        Queen q9 = new Queen("Black");
        Queen q10 = new Queen("Black");
        Queen q11 = new Queen("Black");
        Queen q12 = new Queen("Black");
        Queen q13 = new Queen("Black");
        Queen q14 = new Queen("Black");
        Queen q15 = new Queen("Black");
        Queen q16 = new Queen("Black");
        queens.add(q9);
        queens.add(q10);
        queens.add(q11);
        queens.add(q12);
        queens.add(q13);
        queens.add(q14);
        queens.add(q15);
        queens.add(q16);
        return queens;
    }

    private ArrayList<Piece> customWhiteRooks() {
        ArrayList<Piece> rooks = new ArrayList<Piece>();
        Rook r1 = new Rook("White");
        Rook r2 = new Rook("White");
        Rook r3 = new Rook("White");
        Rook r4 = new Rook("White");
        Rook r5 = new Rook("White");
        Rook r6 = new Rook("White");
        Rook r7 = new Rook("White");
        Rook r8 = new Rook("White");
        rooks.add(r1);
        rooks.add(r2);
        rooks.add(r3);
        rooks.add(r4);
        rooks.add(r5);
        rooks.add(r6);
        rooks.add(r7);
        rooks.add(r8);
        return rooks;
    }

    private ArrayList<Piece> customBlackRooks() {
        ArrayList<Piece> rooks = new ArrayList<Piece>();
        Rook r9 = new Rook("Black");
        Rook r10 = new Rook("Black");
        Rook r11 = new Rook("Black");
        Rook r12 = new Rook("Black");
        Rook r13 = new Rook("Black");
        Rook r14 = new Rook("Black");
        Rook r15 = new Rook("Black");
        Rook r16 = new Rook("Black");
        rooks.add(r9);
        rooks.add(r10);
        rooks.add(r11);
        rooks.add(r12);
        rooks.add(r13);
        rooks.add(r14);
        rooks.add(r15);
        rooks.add(r16);
        return rooks;
    }

    private ArrayList<Piece> customWhiteBishops() {
        ArrayList<Piece> bishops = new ArrayList<Piece>();
        Bishop b1 = new Bishop("White");
        Bishop b2 = new Bishop("White");
        Bishop b3 = new Bishop("White");
        Bishop b4 = new Bishop("White");
        Bishop b5 = new Bishop("White");
        Bishop b6 = new Bishop("White");
        Bishop b7 = new Bishop("White");
        Bishop b8 = new Bishop("White");
        bishops.add(b1);
        bishops.add(b2);
        bishops.add(b3);
        bishops.add(b4);
        bishops.add(b5);
        bishops.add(b6);
        bishops.add(b7);
        bishops.add(b8);
        return bishops;
    }

    private ArrayList<Piece> customBlackBishops() {
        ArrayList<Piece> bishops = new ArrayList<Piece>();
        Bishop b9 = new Bishop("Black");
        Bishop b10 = new Bishop("Black");
        Bishop b11 = new Bishop("Black");
        Bishop b12 = new Bishop("Black");
        Bishop b13 = new Bishop("Black");
        Bishop b14 = new Bishop("Black");
        Bishop b15 = new Bishop("Black");
        Bishop b16 = new Bishop("Black");
        bishops.add(b9);
        bishops.add(b10);
        bishops.add(b11);
        bishops.add(b12);
        bishops.add(b13);
        bishops.add(b14);
        bishops.add(b15);
        bishops.add(b16);
        return bishops;
    }


    private ArrayList<Piece> customWhiteKnights() {
        ArrayList<Piece> knights = new ArrayList<Piece>();
        Knight kn1 = new Knight("White");
        Knight kn2 = new Knight("White");
        Knight kn3 = new Knight("White");
        Knight kn4 = new Knight("White");
        Knight kn5 = new Knight("White");
        Knight kn6 = new Knight("White");
        Knight kn7 = new Knight("White");
        Knight kn8 = new Knight("White");
        knights.add(kn1);
        knights.add(kn2);
        knights.add(kn3);
        knights.add(kn4);
        knights.add(kn5);
        knights.add(kn6);
        knights.add(kn7);
        knights.add(kn8);
        return knights;
    }
    
    private ArrayList<Piece> customBlackKnights() {
        ArrayList<Piece> knights = new ArrayList<Piece>();
        Knight kn9 = new Knight("Black");
        Knight kn10 = new Knight("Black");
        Knight kn11 = new Knight("Black");
        Knight kn12 = new Knight("Black");
        Knight kn13 = new Knight("Black");
        Knight kn14 = new Knight("Black");
        Knight kn15 = new Knight("Black");
        Knight kn16 = new Knight("Black");
        knights.add(kn9);
        knights.add(kn10);
        knights.add(kn11);
        knights.add(kn12);
        knights.add(kn13);
        knights.add(kn14);
        knights.add(kn15);
        knights.add(kn16);
        return knights;
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


