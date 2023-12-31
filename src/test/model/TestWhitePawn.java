package model;

import model.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestWhitePawn {
    Pawn x;

    @BeforeEach
    public void setup(){
        x = new Pawn("White");
    }

    @Test
    public void testcanMove(){
        assertTrue(x.canMove(1, 6, 1, 4));
        assertTrue(x.canMove(1, 1, 1, 0));
        assertFalse(x.canMove(2, 1, 2, 2));
        assertTrue(x.canMove(1, 6, 1, 5));
    }
}

