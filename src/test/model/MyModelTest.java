package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestBoard {
    Board x;

    @BeforeEach
    public void setup(){
    x = new Board();
}

    @Test
    public void testAddGetPiece(){
    King p = new King("White");
    x.addPiece(p, 5, 5);
    assertEquals(p, x.getPiece(5,5));
}

    @Test
    public void testRemovePiece(){
    King p = new King("White");
    x.addPiece(p, 5, 5);
    x.removePiece(5,5);
    assertEquals(null, x.getPiece(5,5));

    }

    @Test
    public void testVisionDiagonal1(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        x.addPiece(b, 1, 1);
        x.addPiece(p1, 3, 3);
        x.addPiece(p2, 5, 5);
        assertFalse(x.visionDiagonal(b, 5, 5));
        assertTrue(x.visionDiagonal(b, 3, 3));
    }

    @Test
    public void testVisionDiagonal2(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        x.addPiece(b, 3, 3);
        x.addPiece(p1, 4, 2);
        x.addPiece(p2, 5, 1);
        assertFalse(x.visionDiagonal(b, 5, 1));
        assertFalse(x.visionDiagonal(b, 6, 0));
        assertTrue(x.visionDiagonal(b, 4, 2));
    }

    @Test
    public void testVisionDiagonal3(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        x.addPiece(b, 3, 3);
        x.addPiece(p1, 2, 2);
        x.addPiece(p2, 1, 1);
        assertFalse(x.visionDiagonal(b, 0, 0));
        assertFalse(x.visionDiagonal(b, 1, 1));
        assertTrue(x.visionDiagonal(b, 2, 2));
    }

    @Test
    public void testVisionDiagonal4(){
        Bishop b = new Bishop("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        x.addPiece(b, 3, 3);
        x.addPiece(p1, 1, 5);
        x.addPiece(p2, 2, 4);
        assertFalse(x.visionDiagonal(b, 0, 6));
        assertFalse(x.visionDiagonal(b, 1, 5));
        assertTrue(x.visionDiagonal(b, 2, 4));
    }
    @Test
    public void testVisionStraight(){
        Rook r = new Rook("White");
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        Pawn p3 = new Pawn("White");
        Pawn p4 = new Pawn("White");
        x.addPiece(r, 3, 3);
        x.addPiece(p1, 2, 3);
        x.addPiece(p2, 3, 2);
        x.addPiece(p3, 3, 4);
        x.addPiece(p4, 4, 3);
        assertTrue(x.visionStraight(r, 2, 3));
        assertTrue(x.visionStraight(r, 3, 2));
        assertTrue(x.visionStraight(r, 3, 4));
        assertTrue(x.visionStraight(r, 4, 3));
        assertFalse(x.visionStraight(r, 3, 5));
        assertFalse(x.visionStraight(r, 1, 3));
        assertFalse(x.visionStraight(r, 3, 1));
        assertFalse(x.visionStraight(r, 6, 3));
    }
}

