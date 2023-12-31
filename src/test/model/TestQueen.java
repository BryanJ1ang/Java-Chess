package model;

import model.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestQueen {
    Queen x;

    @BeforeEach
    public void setup(){
        x = new Queen("White");
    }


    @Test
    public void testGetType() {
        assertEquals("Queen", x.getType());
    }

    @Test
    public void testGetMoved() {
        assertFalse(x.getMoved());
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
