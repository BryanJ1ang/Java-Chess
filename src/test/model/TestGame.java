package model;

import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    Game g;
    Rook r1;
    Rook r2;

    @BeforeEach
    public void setup(){
         g = new Game("default", null, true);
         r1 = new Rook("White");
         r2 = new Rook("Black");
    }

    @Test
    public void testAddPiece(){
        g.addPiece(r1, 3, 3);
        g.addPiece(r2, 5, 5);
        assertEquals(r1, g.getBd().getPiece(3,3));
        assertEquals(r2, g.getBd().getPiece(5,5));
    }

    @Test
    public void testMovePieceAndIsPieceToMove() {
        g.addPiece(r1, 3, 3);
        g.addPiece(r2, 5, 5);

        assertFalse(g.isPieceTurnToMove(r2));
        assertTrue(g.isPieceTurnToMove(r1));
        assertTrue(g.getPlayer1turn());
        assertFalse(g.getPlayer2turn());

        g.movePiece(r1, 3, 5);
        g.swapTurns();
        assertTrue(g.getPlayer2turn());
        assertFalse(g.getPlayer1turn());
        assertEquals(r1, g.getBd().getPiece(3,5));
        assertFalse(g.isPieceTurnToMove(r1));
        assertTrue(g.isPieceTurnToMove(r2));

        g.movePiece(r2, 5, 3);
        g.swapTurns();
        assertFalse(g.isPieceTurnToMove(r2));
        assertTrue(g.isPieceTurnToMove(r1));
        assertTrue(g.getPlayer1turn());
        assertFalse(g.getPlayer2turn());
    }


    @Test
    public void testWhiteBackRow() {
        Piece p;
        p = g.getBd().getPiece(0, 7);
        assertTrue(p instanceof Rook);
        p = g.getBd().getPiece(1, 7);
        assertTrue(p instanceof Knight);
        p = g.getBd().getPiece(2, 7);
        assertTrue(p instanceof Bishop);
        p = g.getBd().getPiece(3, 7);
        assertTrue(p instanceof Queen);
        p = g.getBd().getPiece(4, 7);
        assertTrue(p instanceof King);
        p = g.getBd().getPiece(5, 7);
        assertTrue(p instanceof Bishop);
        p = g.getBd().getPiece(6, 7);
        assertTrue(p instanceof Knight);
        p = g.getBd().getPiece(7, 7);
        assertTrue(p instanceof Rook);
    }

    @Test
    public void testBlackBackRow() {
        Piece p;
        p = g.getBd().getPiece(0, 0);
        assertTrue(p instanceof Rook);
        p = g.getBd().getPiece(1, 0);
        assertTrue(p instanceof Knight);
        p = g.getBd().getPiece(2, 0);
        assertTrue(p instanceof Bishop);
        p = g.getBd().getPiece(3, 0);
        assertTrue(p instanceof Queen);
        p = g.getBd().getPiece(4, 0);
        assertTrue(p instanceof King);
        p = g.getBd().getPiece(5, 0);
        assertTrue(p instanceof Bishop);
        p = g.getBd().getPiece(6, 0);
        assertTrue(p instanceof Knight);
        p = g.getBd().getPiece(7, 0);
        assertTrue(p instanceof Rook);
    }

    @Test
    public void testWhitePawns() {
        Piece p;
        for (int x = 0; x < 7; x++) {
            p = g.getBd().getPiece(x, 6);
            assertTrue(p instanceof Pawn);
        }
    }

    @Test
    public void testBlackPawns() {
        Piece p;
        for (int x = 0; x < 7; x++) {
            p = g.getBd().getPiece(x, 1);
            assertTrue(p instanceof Pawn);
        }
    }

    @Test
    public void testPawnCanBeMovedThere() {
        assertTrue(g.validMove(g.getBd().getPiece(4, 6), 4, 4));
        assertFalse(g.validMove(g.getBd().getPiece(4, 6), 4, 7));
    }

    @Test
    public void testRookCanBeMovedThere() {
        assertFalse(g.validMove(g.getBd().getPiece(0, 7), 0, 6));
        g.getBd().removePiece(0,6);
        assertTrue(g.validMove(g.getBd().getPiece(0, 7), 0, 6));
        assertTrue(g.validMove(g.getBd().getPiece(0, 7), 0, 1));
        assertFalse(g.validMove(g.getBd().getPiece(0, 7), 0, 0));
    }

    @Test
    public void testChangeTurn() {
        assertTrue(g.getPlayer1turn());
        assertFalse(g.getPlayer2turn());
        g.swapTurns();
        assertTrue(g.getPlayer2turn());
        assertFalse(g.getPlayer1turn());
    }

    @Test
    public void testKnightCanBeMovedThere() {
        Piece pawn = new Pawn("White");
        assertTrue(g.validMove(g.getBd().getPiece(1, 7), 2, 5));
        g.getBd().addPiece(pawn, 2,5);
        assertFalse(g.validMove(g.getBd().getPiece(1, 7), 2, 5));
        assertFalse(g.validMove(g.getBd().getPiece(0, 7), 0, 0));
    }

    @Test
    public void testBishopCanBeMovedThere() {
        assertFalse(g.validMove(g.getBd().getPiece(2, 7), 3, 6));
        g.getBd().removePiece(3,6);
        assertTrue(g.validMove(g.getBd().getPiece(2, 7), 3, 6));
        assertFalse(g.validMove(g.getBd().getPiece(1, 7), 7, 7));
        assertFalse(g.validMove(g.getBd().getPiece(0, 7), 0, 0));
    }

    @Test
    public void testQueenCanBeMovedThere() {
        assertFalse(g.validMove(g.getBd().getPiece(3, 7), 3, 6));
        assertFalse(g.validMove(g.getBd().getPiece(3, 7), 3, 3));
        assertFalse(g.validMove(g.getBd().getPiece(3, 7), 3, 0));
        g.getBd().removePiece(3,6);
        assertTrue(g.validMove(g.getBd().getPiece(3, 7), 3, 6));
        assertTrue(g.validMove(g.getBd().getPiece(3, 7), 3, 1));
        assertTrue(g.getBd().getPiece(3,1) instanceof Pawn);
        assertFalse(g.validMove(g.getBd().getPiece(3, 7), 7, 7));
        assertFalse(g.validMove(g.getBd().getPiece(3, 7), 0, 0));
    }

    @Test
    public void testKingCanBeMovedThere() {
        Piece king = g.getBd().getPiece(4, 7);
        assertFalse(g.validMove(g.getBd().getPiece(4, 7), 4, 6));
        g.getBd().removePiece(4,6);
        assertTrue(g.validMove(g.getBd().getPiece(4, 7), 4, 6));
        g.getBd().movePiece(king, 4, 6);
        assertTrue(g.validMove(g.getBd().getPiece(4, 6), 3, 5));
        assertTrue(g.validMove(g.getBd().getPiece(4, 6), 4, 5));
        assertTrue(g.validMove(g.getBd().getPiece(4, 6), 5, 5));
        assertFalse(g.validMove(g.getBd().getPiece(4, 6), 3, 4));
        assertFalse(g.validMove(g.getBd().getPiece(4, 6), 4, 4));
        assertFalse(g.validMove(g.getBd().getPiece(4, 6), 5, 4));

    }

    @Test
    public void testGameFalse() {
        g = new Game("default", null, false);
        assertFalse(g.getPlayer1turn());
        assertTrue(g.getPlayer2turn());
    }

}
