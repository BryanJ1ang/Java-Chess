package model;

import model.pieces.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKnight {
    Knight x;

    @BeforeEach

    public void setup() {
        x = new Knight("White");
    }

    @Test
    public void testGetType() {
        assertEquals("Knight", x.getType());
    }

    @Test
    public void testGetMoved() {
        assertFalse(x.getMoved());
    }

    @Test
    public void TestCanMove(){
        assertTrue(x.canMove(3,3,5, 4));
        assertTrue(x.canMove(3,3,5, 2));
        assertTrue(x.canMove(3,3,4, 5));
        assertTrue(x.canMove(3,3,2, 5));
        assertTrue(x.canMove(3,3,1, 2));
        assertTrue(x.canMove(3,3,1, 4));

        assertFalse(x.canMove(3,3,5, 5));
        assertFalse(x.canMove(3,3,5, 5));
        assertFalse(x.canMove(3,3,1, 1));
        assertFalse(x.canMove(3,3,5, 5));
        assertFalse(x.canMove(3,3,3, 2));
        assertFalse(x.canMove(3,3,0, 0));

        assertTrue(x.canMove(3,3,2, 5));
        assertTrue(x.canMove(3,3,4, 5));
        assertTrue(x.canMove(3,3,2, 1));
        assertTrue(x.canMove(3,3,4, 1));
    }
}
