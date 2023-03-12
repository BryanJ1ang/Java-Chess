package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter {
    Game g;
    JsonWriter jwriter;
    @BeforeEach
    public void setup() {
         g = new Game("default", null, true);
         jwriter = new JsonWriter("./data/testreader.json");
    }

    @Test
    public void testWriteg() {
        try {
            jwriter.open();
            jwriter.write(g);
            jwriter.close();
        }
        catch (Exception filenotfound) {
            fail("Exception should not have been thrown");
        }

        JsonReader jreader = new JsonReader("./data/testreader.json");

        try {
            g = jreader.read();
            Piece p;

            p = g.getBd().getPiece(0, 7);
            assertTrue(p.getType().equals("Rook"));
            assertTrue(p.getWhite());
            p = g.getBd().getPiece(1, 7);
            assertTrue(p.getWhite());
            assertTrue(p.getType().equals("Knight"));
            p = g.getBd().getPiece(2, 7);
            assertTrue(p.getWhite());
            assertTrue(p.getType().equals("Bishop"));
            assertTrue(p.getWhite());
            p = g.getBd().getPiece(3, 7);
            assertTrue(p.getType().equals("Queen"));
            assertTrue(p.getWhite());
            p = g.getBd().getPiece(4, 7);
            assertTrue(p.getType().equals("King"));
            assertTrue(p.getWhite());
            p = g.getBd().getPiece(5, 7);
            assertTrue(p.getType().equals("Bishop"));
            assertTrue(p.getWhite());
            p = g.getBd().getPiece(6, 7);
            assertTrue(p.getType().equals("Knight"));
            assertTrue(p.getWhite());
            p = g.getBd().getPiece(7, 7);
            assertTrue(p.getType().equals("Rook"));
            assertTrue(p.getWhite());


            p = g.getBd().getPiece(0, 0);
            assertTrue(p.getType().equals("Rook"));
            assertFalse(p.getWhite());
            p = g.getBd().getPiece(1, 0);
            assertFalse(p.getWhite());
            assertTrue(p.getType().equals("Knight"));
            assertFalse(p.getWhite());
            p = g.getBd().getPiece(2, 0);
            assertTrue(p.getType().equals("Bishop"));
            assertFalse(p.getWhite());
            p = g.getBd().getPiece(3, 0);
            assertTrue(p.getType().equals("Queen"));
            assertFalse(p.getWhite());
            p = g.getBd().getPiece(4, 0);
            assertTrue(p.getType().equals("King"));
            assertFalse(p.getWhite());
            p = g.getBd().getPiece(5, 0);
            assertTrue(p.getType().equals("Bishop"));
            assertFalse(p.getWhite());
            p = g.getBd().getPiece(6, 0);
            assertTrue(p.getType().equals("Knight"));
            assertFalse(p.getWhite());
            p = g.getBd().getPiece(7, 0);
            assertTrue(p.getType().equals("Rook"));
            assertFalse(p.getWhite());


            for (int x = 0; x < 7; x++) {
                p = g.getBd().getPiece(x, 6);
                assertTrue(p.getType().equals("Pawn"));
                assertTrue(p.getWhite());
            }

            for (int x = 0; x < 7; x++) {
                p = g.getBd().getPiece(x, 1);
                assertTrue(p.getType().equals("Pawn"));
                assertFalse(p.getWhite());
            }
        }
        catch (Exception woah) {
            fail("Exception should not have been thrown");
        }
    }
}
