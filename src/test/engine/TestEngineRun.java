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


    // alpha beta depth 6, 23s
    // standard depth 4, 2.69s | alpha beta 0.196s
    // alpha depth 6, 17.7s
    @Test
    public void testRun() { // tests for completion
        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 5); //ADJUST  DEPTH
        System.out.println(bestmove.getValue0().getType());
        System.out.println(bestmove.getValue1());
        System.out.println(bestmove.getValue2());
        assertFalse(bestmove == null);

    }

    // standard depth 4, 39.344s | alpha beta 2.184ms
    // alpha beta depth 6, 2min 24s
    @Test
    public void testEngineEarlyGame() {
        game.movePiece(game.getBd().getPiece(4,6),4,4 ); // E4
        game.movePiece(game.getBd().getPiece(4,1),4,3 ); // E5

        game.movePiece(game.getBd().getPiece(3,7),6,4 ); // Queen to G4
        game.movePiece(game.getBd().getPiece(3,1),3,3 ); // D5


        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 5); //ADJUST  DEPTH

        System.out.println(bestmove.getValue0().getType());
        System.out.println(bestmove.getValue1());
        System.out.println(bestmove.getValue2());

    }

    // alphabeta 13.456s
    // minimax 13.8s
    // alphabeta depth 6, 3:57s queen
    // Tests if engine identifies simple one move checkmate
    //
    // alphaBeta optimized with capture priority 2.640s, depth 4
    // minimax optimized with capture priority, 41.650s depth 4
    // alpha beta depth 6, +


    @Test
    public void testScholarsMate() {
        game.movePiece(game.getBd().getPiece(4,6),4,4 ); //E4
        game.movePiece(game.getBd().getPiece(3,7),7,3 );
        game.movePiece(game.getBd().getPiece(1,0),2,2 ); //knc6
        game.movePiece(game.getBd().getPiece(5,7),2,4 );
        game.movePiece(game.getBd().getPiece(4,1),4,3 ); //E5
        game.movePiece(game.getBd().getPiece(0,1),5,3 ); //F5



        Triplet<Piece, Integer , Integer> bestmove = engine.returnBestMove(game, 5); //ADJUST  DEPTH
        assertEquals(bestmove.getValue0().getType(), "Queen");
        assertEquals(bestmove.getValue1(), 5);
        assertEquals(bestmove.getValue2(), 1);
    }


}
