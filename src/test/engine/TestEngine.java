package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.LinkedList;
import java.util.List;

public class TestEngine {
    Game game, game2;
    Engine engine;
    List<Triplet<Piece, Integer , Integer>> legalMovesList;

    @BeforeEach
    public void setup() {
        game = new Game("default", null, true);
        game2 = new Game("default", null, true);
        legalMovesList = new LinkedList<>();
        game2.movePiece(game2.getBd().getPiece(4,6),4, 4); //E2 - E4
        game2.movePiece(game2.getBd().getPiece(3,1),3, 3); // D5
        engine = new Engine();
    }


    @Test
    public void testMovePiece() {
        Piece pawn = game.getBd().getPiece(4,6);
        Piece pawn2 = game2.getBd().getPiece(4,6);
        Piece pawn3 = game2.getBd().getPiece(4,6);
        assertNull(engine.movePiece(game, pawn, 4, 4));

    }

    @Test
    public void testEnPassanAddRemoval() {
        Piece piece, piece2;
        piece = game.getBd().getPiece(4, 6);
        game.movePiece(piece, 4, 3);
        piece2 = game.getBd().getPiece(3, 1);
        game.movePiece(piece2, 3, 3);

        engine.movePiece(game,piece, 3,2);
        engine.returnPiece(game, piece,piece2, 4,3,3,2);
        assertTrue(game.getBd().getPiece(4,3) == piece);
        assertFalse(game.getBd().getPiece(3,2) == piece2);
        assertEquals(game.getBd().getPiece(3,3), piece2);
    }


    @Test
    public void testMovePiece2() {
        Piece pawn2 = game2.getBd().getPiece(4,4);
        Piece pawn3 = game2.getBd().getPiece(3,3);
        assertTrue(game2.getPlayer2().getPieces().contains(pawn3));
        assertEquals(engine.movePiece(game2, pawn2, 3, 3), pawn3);
        assertFalse(game2.getPlayer2().getPieces().contains(pawn3));
    }

    @Test
    public void testReturnPiece() {
        Piece pawn2 = game2.getBd().getPiece(4,4);
        Piece pawn3 = game2.getBd().getPiece(3,3);
        assertTrue(game2.getPlayer2().getPieces().contains(pawn3));
        assertEquals(engine.movePiece(game2, pawn2, 3, 3), pawn3);
        assertFalse(game2.getPlayer2().getPieces().contains(pawn3));
        engine.returnPiece(game2, pawn2, pawn3, 4,4,3,3);
        assertTrue(game2.getPlayer2().getPieces().contains(pawn3));

        assertEquals(3, pawn3.getXposition() );
        assertEquals(3, pawn3.getYposition() );

        assertEquals(4, pawn2.getXposition() );
        assertEquals(4, pawn2.getYposition() );
        assertTrue(game2.getBd().getPiece(3,3) == pawn3);
        assertTrue(game2.getBd().getPiece(4,4) == pawn2);
    }

    @Test
    public void testGameEvaluation() {
        assertEquals(engine.evaluateGameState(game), 0);
        game.movePiece(game.getBd().getPiece(3,7),3,0 );
        assertEquals(engine.evaluateGameState(game), 9);
    }


    @Test
    public void testRun() {
        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 2);
        System.out.println(bestmove.getValue0());
        System.out.println(bestmove.getValue1());
        System.out.println(bestmove.getValue2());

    }



}
