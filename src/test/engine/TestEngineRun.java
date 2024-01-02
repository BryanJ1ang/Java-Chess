package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestEngineRun {

    Game game;
    Engine engine;
    List<Triplet<Piece, Integer , Integer>> legalMovesList;

    @BeforeEach
    public void setup() {
        game = new Game("default", null, true);
        legalMovesList = new LinkedList<>();
        engine = new Engine();
    }

    @Test
    public void testRun() { // tests for completion
        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 2); //ADJUST  DEPTH
        System.out.println(bestmove.getValue0().getType());
        System.out.println(bestmove.getValue1());
        System.out.println(bestmove.getValue2());
        assertFalse(bestmove == null);

    }

    @Test // 30s to 3s
    public void testEngineEarlyGame() {
        game.movePiece(game.getBd().getPiece(4,6),4,4 ); // E4
        game.movePiece(game.getBd().getPiece(4,1),4,3 ); // E5

        game.movePiece(game.getBd().getPiece(3,7),6,4 ); // Queen to G4
        game.movePiece(game.getBd().getPiece(3,1),3,3 ); // D5

        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 4); //ADJUST  DEPTH

        System.out.println(bestmove.getValue0().getType());
        System.out.println(bestmove.getValue1());
        System.out.println(bestmove.getValue2());

    }

    // alphabeta 13.456s
    // minimax 13.8s
    // Tests if engine identifies simple one move checkmate
    @Test
    public void testScholarsMate() {
        game.movePiece(game.getBd().getPiece(4,6),4,4 );
        game.movePiece(game.getBd().getPiece(3,7),6,4 );
        game.movePiece(game.getBd().getPiece(5,7),2,3 );

        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 2); //ADJUST  DEPTH
        assertEquals(bestmove.getValue0().getType(), "Bishop");
        assertEquals(bestmove.getValue1(), 4);
        assertEquals(bestmove.getValue2(), 1);
    }


}
