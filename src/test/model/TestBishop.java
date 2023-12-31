package model;

import model.pieces.Bishop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBishop {

    Bishop x;

    @BeforeEach
    public void setup(){
        x = new Bishop("White");
    }

    @Test
    public void testGetType() {
        assertEquals("Bishop", x.getType());
    }

    @Test
    public void testGetMoved() {
        assertFalse(x.getMoved());
    }

    @Test
    public void testCanMove() {
        assertFalse(x.canMove(5, 5, 5, 7));
        // MOVE UP 2
        assertFalse(x.canMove(5, 5, 7, 6));
        assertFalse(x.canMove(5, 5, 3, 5));
        assertFalse(x.canMove(5, 5, 2, 7));
        assertTrue(x.canMove(5, 5, 4, 6));
        assertTrue(x.canMove(5, 5, 6, 4));

        assertTrue(x.canMove(5, 5, 6, 6));
        assertTrue(x.canMove(5, 5, 4, 4));
    }
}
