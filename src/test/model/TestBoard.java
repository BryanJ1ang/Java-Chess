package model;


import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestBoard {
    Board bd;

    @BeforeEach
    public void setup(){
    bd = new Board();
}

    @Test
    public void testAddGetPiece(){
    King p = new King("White");
    bd.addPiece(p, 5, 5);
    assertEquals(p, bd.getPiece(5,5));
}

    @Test
    public void testRemovePiece(){
    King p = new King("White");
    bd.addPiece(p, 5, 5);
    bd.removePiece(5,5);
    assertNull(bd.getPiece(5,5));

    }

    @Test
    public void testVisionDiagonal1(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        bd.addPiece(b, 1, 1);
        bd.addPiece(p1, 3, 3);
        bd.addPiece(p2, 5, 5);
        assertFalse(bd.visionDiagonal(b, 5, 5));
        assertTrue(bd.visionDiagonal(b, 3, 3));
    }

    @Test
    public void testVisionDiagonal2(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        bd.addPiece(b, 3, 3);
        bd.addPiece(p1, 4, 2);
        bd.addPiece(p2, 5, 1);
        assertFalse(bd.visionDiagonal(b, 5, 1));
        assertFalse(bd.visionDiagonal(b, 6, 0));
        assertTrue(bd.visionDiagonal(b, 4, 2));
    }

    @Test
    public void testVisionDiagonal3(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        bd.addPiece(b, 3, 3);
        bd.addPiece(p1, 2, 2);
        bd.addPiece(p2, 1, 1);
        assertFalse(bd.visionDiagonal(b, 0, 0));
        assertFalse(bd.visionDiagonal(b, 1, 1));
        assertTrue(bd.visionDiagonal(b, 2, 2));
    }

    @Test
    public void testVisionDiagonal4(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        bd.addPiece(b, 3, 3);
        bd.addPiece(p1, 1, 5);
        bd.addPiece(p2, 2, 4);
        assertFalse(bd.visionDiagonal(b, 0, 6));
        assertFalse(bd.visionDiagonal(b, 1, 5));
        assertTrue(bd.visionDiagonal(b, 2, 4));
    }
    @Test
    public void testVisionStraight(){
        Rook r = new Rook("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        Pawn p3 = new Pawn("White");
        Pawn p4 = new Pawn("White");
        bd.addPiece(r, 3, 3);
        bd.addPiece(p1, 2, 3);
        bd.addPiece(p2, 3, 2);
        bd.addPiece(p3, 3, 4);
        bd.addPiece(p4, 4, 3);
        assertTrue(bd.visionStraight(r, 2, 3));
        assertTrue(bd.visionStraight(r, 3, 2));
        assertTrue(bd.visionStraight(r, 3, 4));
        assertTrue(bd.visionStraight(r, 4, 3));
        assertFalse(bd.visionStraight(r, 3, 5));
        assertFalse(bd.visionStraight(r, 1, 3));
        assertFalse(bd.visionStraight(r, 3, 1));
        assertFalse(bd.visionStraight(r, 6, 3));
    }

    @Test
    public void testVisionStraight2(){
        Rook r = new Rook("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        Pawn p3 = new Pawn("White");
        Pawn p4 = new Pawn("Black");
        bd.addPiece(r, 3, 7);
        assertTrue(bd.visionStraight(r, 3, 0));
        bd.addPiece(p4, 3,1);
        assertFalse(bd.visionStraight(r, 3, 0));
    }
    @Test
    public void testCheck1(){
        Rook r1 = new Rook("White");
        Rook r2 = new Rook("Black");
        King k = new King("Black");
        bd.addPiece(k, 4,4);
        assertFalse(bd.check(k));
        bd.addPiece(r2, 2,4);
        assertFalse(bd.check(k));
        bd.addPiece(r1, 6,4);
        assertTrue(bd.check(k));
    }

    @Test
    public void testCheck2(){
        Bishop b1 = new Bishop("White");
        Bishop b2 = new Bishop("Black");
        King k = new King("Black");
        bd.addPiece(k, 4,4);
        assertFalse(bd.check(k));
        bd.addPiece(b2, 6,6);
        assertFalse(bd.check(k));
        bd.addPiece(b1, 2,2);
        assertTrue(bd.check(k));
        bd.addPiece(b2, 3,3);
        assertFalse(bd.check(k));
    }

    @Test
    public void testCheck3(){
        Queen b1 = new Queen("White");
        Queen b2 = new Queen("Black");
        Queen b3 = new Queen("White");
        King k = new King("Black");
        bd.addPiece(k, 4,4);
        assertFalse(bd.check(k));
        bd.addPiece(b2, 6,6);
        assertFalse(bd.check(k));
        bd.addPiece(b1, 2,2);
        assertTrue(bd.check(k));
        bd.addPiece(b2, 3,3);
        assertFalse(bd.check(k));
        bd.addPiece(b3, 4, 2);
        assertTrue(bd.check(k));
    }

    @Test
    public void testCheck4(){
        Knight b1 = new Knight("White");
        Knight b2 = new Knight("Black");
        Knight b3 = new Knight("White");
        King k = new King("Black");
        bd.addPiece(k, 4,4);
        assertFalse(bd.check(k));
        bd.addPiece(b2, 2,3);
        bd.addPiece(b1, 6,5);
        assertTrue(bd.check(k));
        bd.removePiece(6,5);
        bd.addPiece(b1, 3,2);
        assertTrue(bd.check(k));
    }

    @Test
    public void testCheck5(){
        Pawn b1 = new Pawn("White");
        Pawn b2 = new Pawn("Black");
        Pawn b3 = new Pawn("White");
        King k = new King("Black");
        bd.addPiece(k, 4,4);
        assertFalse(bd.check(k));
        bd.addPiece(b1, 3,5);
        bd.addPiece(b3, 5,5);
        assertTrue(bd.check(k));
        bd.removePiece(3,3);
        assertTrue(bd.check(k));
    }

    @Test
    public void testCheck6(){
        Pawn b1 = new Pawn("Black");
        Pawn b2 = new Pawn("Black");
        Pawn b3 = new Pawn("Black");
        King k = new King("White");
        bd.addPiece(k, 4,4);
        assertFalse(bd.check(k));
        bd.addPiece(b1, 3,3);
        bd.addPiece(b3, 5,3);
        assertTrue(bd.check(k));
        bd.removePiece(3,3);
        assertTrue(bd.check(k));
    }
}

