package model;

import model.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestRook {
    Rook x;

    @BeforeEach
    public void setup(){
        x = new Rook("White");
    }

    @Test
    public void testGetType() {
        assertEquals("Rook", x.getType());
    }

    @Test
    public void testGetMoved() {
        assertFalse(x.getMoved());
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
