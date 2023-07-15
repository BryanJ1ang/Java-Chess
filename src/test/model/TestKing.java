package model;

import model.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKing {

    King x;
    @BeforeEach
    public void setup(){
        x = new King("White");
    }

    @Test
    public void testGetType() {
        assertEquals("King", x.getType());
    }


    @Test
    public void testConstructior() {
        King k = new King("whitE");
        assertTrue(k.white);
    }

    @Test
    public void testGetMoved() {
        assertFalse(x.getMoved());
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
}
