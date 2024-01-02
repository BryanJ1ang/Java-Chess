package model;

import model.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestPawn {
    Pawn x;

    @BeforeEach
    public void setup(){
        x = new Pawn("Black");
    }

    @Test
    public void testGetType() {
        assertEquals("Pawn", x.getType());
    }

    @Test
    public void testGetMoved() {
        assertFalse(x.getMoved());
    }

    @Test
    public void testColorWhite(){
        assertEquals(false, x.isWhite());
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
