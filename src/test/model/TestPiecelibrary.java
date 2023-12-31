package model;

import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPiecelibrary {
    Piecelibrary c;

    @BeforeEach
    public void setup() {
        c = new Piecelibrary();
    }

    @Test
    public void testRetrieveWhite() {
        assertTrue(c.retrievePieceFromLibrary("KING", "WHITE") instanceof King);
        assertTrue(c.retrievePieceFromLibrary("QUEEN", "WHITE") instanceof Queen);
        assertTrue(c.retrievePieceFromLibrary("PAWN", "WHITE") instanceof Pawn);
        assertTrue(c.retrievePieceFromLibrary("BISHOP", "WHITE") instanceof Bishop);
        assertTrue(c.retrievePieceFromLibrary("KNIGHT", "WHITE") instanceof Knight);
        assertTrue(c.retrievePieceFromLibrary("ROOK", "WHITE") instanceof Rook);
    }

    @Test
    public void testRetrieveBlack() {
        assertTrue(c.retrievePieceFromLibrary("KING", "BLACK") instanceof King);
        assertTrue(c.retrievePieceFromLibrary("QUEEN", "BLACK") instanceof Queen);
        assertTrue(c.retrievePieceFromLibrary("PAWN", "BLACK") instanceof Pawn);
        assertTrue(c.retrievePieceFromLibrary("BISHOP", "BLACK") instanceof Bishop);
        assertTrue(c.retrievePieceFromLibrary("KNIGHT", "BLACK") instanceof Knight);
        assertTrue(c.retrievePieceFromLibrary("ROOK", "BLACK") instanceof Rook);
    }

    @Test
    public void testRetrieveWhite2() {
        for (int x = 1; x < 9; x++) {
            assertTrue(c.retrievePieceFromLibrary("QUEEN", "WHITE") instanceof Queen);
            assertTrue(c.retrievePieceFromLibrary("PAWN", "WHITE") instanceof Pawn);
            assertTrue(c.retrievePieceFromLibrary("BISHOP", "WHITE") instanceof Bishop);
            assertTrue(c.retrievePieceFromLibrary("KNIGHT", "WHITE") instanceof Knight);
            assertTrue(c.retrievePieceFromLibrary("ROOK", "WHITE") instanceof Rook);
        }
    }


    @Test
    public void testRetrieveBlack2() {
        for (int x = 1; x < 9; x++) {
            assertTrue(c.retrievePieceFromLibrary("QUEEN", "BLACK") instanceof Queen);
            assertTrue(c.retrievePieceFromLibrary("PAWN", "BLACK") instanceof Pawn);
            assertTrue(c.retrievePieceFromLibrary("BISHOP", "BLACK") instanceof Bishop);
            assertTrue(c.retrievePieceFromLibrary("KNIGHT", "BLACK") instanceof Knight);
            assertTrue(c.retrievePieceFromLibrary("ROOK", "BLACK") instanceof Rook);
        }
    }
}
