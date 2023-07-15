package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckTest {
    Game g;
    Game g2;

    @BeforeEach
    public void setUpBoard() {
        g = new Game("default", null, true);
        g2 = new Game("empty", null, true);
    }

    @Test
    public void test1() {
        assertTrue(g.getBd().getPiece(5,6) instanceof Pawn);
        g.movePiece(g.getBd().getPiece(5,6), 5,5 );

        assertTrue(g.getBd().getPiece(4,1) instanceof Pawn);
        g.movePiece(g.getBd().getPiece(4,1),4,3 );

        assertTrue(g.getBd().getPiece(6,6) instanceof Pawn);
        g.movePiece(g.getBd().getPiece(6,6), 5,4 );

        assertTrue(g.getBd().getPiece(3,0) instanceof Queen);
        g.movePiece(g.getBd().getPiece(3,0),7,4 );

        Check check = new Check(g);

        assertNull(g.getBd().getPiece(3,0));
        assertNull(g.getBd().getPiece(5,6));
        assertNull(g.getBd().getPiece(4,1));
        assertNull(g.getBd().getPiece(6,6));

        assertTrue(check.whiteCheck());
        assertTrue(check.isCheckMate());

    }

    @Test
    public void test2() {
        assertTrue(g.getBd().getPiece(5,6) instanceof Pawn);
        g.movePiece(g.getBd().getPiece(5,6), 5,5 );

        assertTrue(g.getBd().getPiece(4,1) instanceof Pawn);
        g.movePiece(g.getBd().getPiece(4,1),4,3 );


        assertTrue(g.getBd().getPiece(3,0) instanceof Queen);
        g.movePiece(g.getBd().getPiece(3,0),7,4 );

        Check check = new Check(g);

        assertNull(g.getBd().getPiece(3,0));
        assertNull(g.getBd().getPiece(5,6));
        assertNull(g.getBd().getPiece(4,1));

        assertTrue(check.whiteCheck());
        assertFalse(check.isCheckMate());

    }

    @Test
    public void test3() {
        King k = new King(true);
        King k2 = new King(false);
        Queen q1 = new Queen(true);
        Queen q11 = new Queen(true);
        Queen q2 = new Queen(false);

        g2.addPiece(k, 0, 0);
        g2.addPiece(k2, 7, 7);

        g2.addPiece(q2, 7, 6);
        Check check = new Check(g2);
        assertFalse(check.moveIntoCheck(q2, 7, 0));
        assertTrue(q2.getXposition() == 7);
        assertTrue(q2.getYposition() == 6);


    }

    @Test
    public void test4() {
        King k = new King(true);
        King k2 = new King(false);
        Queen q1 = new Queen(true);
        Queen q11 = new Queen(true);
        Queen q2 = new Queen(false);

        g2.addPiece(k, 0, 0);
        g2.addPiece(k2, 7, 7);

        g2.addPiece(q2, 7, 6);
        g2.addPiece(q1, 7, 3);
        Check check = new Check(g2);
        assertFalse(check.moveIntoCheck(q2, 7, 4));
        assertTrue(check.moveIntoCheck(q2, 5, 7));
        assertFalse(check.moveIntoCheck(q2, 7, 3));

        assertTrue(q2.getXposition() == 7);
        assertTrue(q2.getYposition() == 6);
        assertTrue(q1.getXposition() == 7);
        assertTrue(q1.getYposition() == 3);

    }

    @Test
    public void test5() {
        King k = new King(true);
        King k2 = new King(false);
        Queen q1 = new Queen(true);
        Queen q11 = new Queen(true);
        Queen q2 = new Queen(false);

        g2.addPiece(k, 0, 0);
        g2.addPiece(k2, 7, 7);

        g2.addPiece(q2, 7, 6);
        g2.addPiece(q1, 7, 3);
        assertTrue(g2.validMove(q2, 7,4));
        assertFalse(g2.validMove(q2, 3,6));
        assertTrue(g2.validMove(q2, 7,3));
        assertFalse(g2.validMove(q2, 7,0));
        assertFalse(g2.validMove(q2, 7,7));

        assertTrue(q2.getXposition() == 7);
        assertTrue(q2.getYposition() == 6);
        assertTrue(q1.getXposition() == 7);
        assertTrue(q1.getYposition() == 3);
        assertTrue(k2.getXposition() == 7);
        assertTrue(k2.getYposition() == 7);


    }

}
