package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

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
        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 5); //ADJUST  DEPTH
        System.out.println(bestmove.getValue0().getType());
        System.out.println(bestmove.getValue1());
        System.out.println(bestmove.getValue2());

    }

    // Tests if engine identifies simple one move checkmate
    @Test
    public void testScholarsMate() {
        game.movePiece(game.getBd().getPiece(4,6),4,4 );
        game.movePiece(game.getBd().getPiece(3,7),6,4 );
        game.movePiece(game.getBd().getPiece(5,7),2,3 );

        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 2); //ADJUST  DEPTH
        System.out.println(bestmove.getValue0().getType());
        System.out.println(bestmove.getValue1());
        System.out.println(bestmove.getValue2());

    }


}
