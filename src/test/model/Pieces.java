package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Testking {
    King x;
    @BeforeEach
    public void setup(){
        x = new King();
    }

    @Test
    public void testcanMove1(){
        assertTrue(x.canMove(5, 5, 4, 4));
        // MOVE 1 BOTTOM LEFT
    }
    @Test
    public void testcanMove2(){
        assertTrue(x.canMove(5, 5, 5, 6));
        // MOVE UP 1

    }

    @Test
    public void testcanMov3(){
        assertTrue(x.canMove(5, 5, 5, 4));
        // MOVE DOWN 1

    }
    @Test
    public void testcanMov4(){
        assertTrue(x.canMove(5, 5, 4, 5));
        // MOVE LEFT 1

    }
    @Test
    public void testcanMove5(){
        assertTrue(x.canMove(5, 5, 6, 5));
        // MOVE RIGHT 1
    }

    @Test
    public void testcanMove6(){
        assertFalse(x.canMove(5, 5, 5, 7));
        // MOVE UP 2
        assertFalse(x.canMove(5, 5, 7, 6));
        assertFalse(x.canMove(5, 5, 3, 5));
        assertFalse(x.canMove(5, 5, 2, 7));
        assertTrue(x.canMove(5, 5, 4, 6));
        assertTrue(x.canMove(5, 5, 6, 6));
    }


}

class testQueen {
    Queen x;

    @BeforeEach
    public void setup(){
        x = new Queen();
    }

    @Test
    public void testcanMove1(){
        assertTrue(x.canMove(5,5, 7, 7));
        assertTrue(x.canMove(5,5, 2, 5));
        assertTrue(x.canMove(5,5, 7, 5));
        assertTrue(x.canMove(5,5, 5, 7));
        assertTrue(x.canMove(5,5, 5, 3));
        assertTrue(x.canMove(5,5, 2,2));
        assertTrue(x.canMove(5,6, 4,5));
        assertTrue(x.canMove(5,5, 7,7));
        assertTrue(x.canMove(5,6, 6,7));
        assertFalse(x.canMove(5,5, 6,7));
        assertTrue(x.canMove(5,5, 6,5));
    }
}


class testRook {
    Rook x;

    @BeforeEach
    public void setup(){
        x = new Rook();
    }

    @Test
    public void testcanMove(){
        assertTrue(x.canMove(5,5,5,7));
        assertTrue(x.canMove(5,5,4,5));
        assertTrue(x.canMove(5,5,0,5));
        assertTrue(x.canMove(5,5,5,3));

        assertFalse(x.canMove(5,5,6,7));
        assertFalse(x.canMove(5,5,4,4));
        assertFalse(x.canMove(5,5,6,6));
        assertFalse(x.canMove(5,5,7,0));

    }
}

class testPawnWhite {
    Pawn x;

    @BeforeEach
    public void setup(){
        x = new Pawn("Black");
    }

    @Test
    public void testColorWhite(){
        assertEquals(false, x.getWhite());
    }

    @Test
    public void testcanMove(){
        assertTrue(x.canMove(3, 1, 3, 3));
        assertTrue(x.canMove(1, 1, 1, 2));
        assertTrue(x.canMove(2, 1, 2, 2));

        assertTrue(x.canMove(5, 1, 5, 2));
        assertTrue(x.canMove(1, 5, 1, 6));
        assertTrue(x.canMove(1, 6, 1, 7));
        assertFalse(x.canMove(1, 1, 1, 4));
        assertFalse(x.canMove(1, 1, 1, 5));
        assertFalse(x.canMove(1, 1, 2, 3));
        assertFalse(x.canMove(1, 1, 2, 2));

    }
}