package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.LinkedList;
import java.util.List;

public class TestEnginePuzzles {

    Game game;
    Engine engine;
    int depth = 4; // adjust for depth of engine search
    List<Triplet<Piece, Integer , Integer>> legalMovesList;
    Triplet<Piece, Integer , Integer> bestMove;

    @BeforeEach
    public void setup() {
        game = new Game("empty", null, true);
        legalMovesList = new LinkedList<>();
        engine = new Engine();
    }

    @Test
    public void testPuzzle600() {
        King king = new King(true);
        King king2 = new King(false);
        game.addPiece(king, 7,7);
        game.addPiece(king2, 4,0);

        Bishop bishop = new Bishop(true);
        game.addPiece(bishop, 0, 5);
        bishop = new Bishop(false);
        game.addPiece(bishop, 1, 1);

        Pawn pawn = new Pawn(true);
        game.addPiece(pawn, 1, 5);
        pawn = new Pawn(true);
        game.addPiece(pawn, 2, 6);
        pawn = new Pawn(true);
        game.addPiece(pawn, 6, 6);
        pawn = new Pawn(true);
        game.addPiece(pawn, 7, 6);

        pawn = new Pawn(false);
        game.addPiece(pawn, 3, 1);
        pawn = new Pawn(false);
        game.addPiece(pawn, 4, 2);

        Rook rook = new Rook(false);
        game.addPiece(rook, 5, 0);
        rook = new Rook(false);
        game.addPiece(rook, 4, 1);

        rook = new Rook(true);
        game.addPiece(rook, 5, 7);
        rook = new Rook(true);
        game.addPiece(rook, 5, 6);

        bestMove = engine.returnBestMove(game, depth);
        assertEquals(bestMove.getValue0().getType(), "Rook");
        assertEquals(bestMove.getValue2(), 0);
        assertEquals(bestMove.getValue1(), 5);

    }


    @Test
    public void testPuzzle650() {
        game = new Game("default", null, true);
        game.movePiece(game.getBd().getPiece(6,6),6,4 );
        game.movePiece(game.getBd().getPiece(5,6),5,4 );
        game.movePiece(game.getBd().getPiece(4,1),4,2);
        game.swapTurns();
        bestMove = engine.returnBestMove(game, 4);
        assertEquals("Queen", bestMove.getValue0().getType());
        assertEquals(7,bestMove.getValue1());
        assertEquals(4, bestMove.getValue2());



    }

    @Test
    public void testPuzzle700() {
        game = new Game("default", null, true);
        game.movePiece(game.getBd().getPiece(3,6),3,4 ); // white pawn
        game.movePiece(game.getBd().getPiece(5,7),1,5); // white bishop
        game.movePiece(game.getBd().getPiece(6,7),4,3); // white knight

        game.movePiece(game.getBd().getPiece(1,0),2,2); // black knight
        game.movePiece(game.getBd().getPiece(3,1),3,2); // black pawn
        game.movePiece(game.getBd().getPiece(2,0),5,3); // black bishop
        game.movePiece(game.getBd().getPiece(6,0),5,2); // black knight

        bestMove = engine.returnBestMove(game, 4);
        assertEquals("Bishop", bestMove.getValue0().getType());
        assertEquals(5,bestMove.getValue1());
        assertEquals(1, bestMove.getValue2());
    }


    // depth 5, 1:13 for mate in 3
    // fixed alpha-beta pruning, 16.558s to solve
    //  depth 5, actual alpha-beta pruning,
    // depth 4, fails to solve
    @Test
    public void testPuzzle1600() {
        King king = new King(true);
        game.addPiece(king, 7,7);
        king = new King(false);
        game.addPiece(king, 6,0);

        Rook rook = new Rook(true);
        game.addPiece(rook, 3,7);
        rook = new Rook(true);
        game.addPiece(rook, 5,6);

        rook = new Rook(false);
        game.addPiece(rook, 0,0);
        rook = new Rook(false);
        game.addPiece(rook, 5,0);

        Knight knight = new Knight(false);
        game.addPiece(knight,7,3);
        knight = new Knight(false);
        game.addPiece(knight,5,4);
        knight = new Knight(true);
        game.addPiece(knight,2,5);
        knight = new Knight(true);
        game.addPiece(knight,7,6);

        Bishop bishop = new Bishop(true);
        game.addPiece(bishop, 1,5);

        Queen queen = new Queen(true);
        game.addPiece(queen, 3,6);
        queen = new Queen(false);
        game.addPiece(queen, 7,5);


        Pawn pawn = new Pawn(false);
        game.addPiece(pawn, 5,1);
        pawn = new Pawn(false);
        game.addPiece(pawn, 6,1);
        pawn = new Pawn(false);
        game.addPiece(pawn, 7,1);

        pawn = new Pawn(false);
        game.addPiece(pawn, 4,3);
        pawn = new Pawn(false);
        game.addPiece(pawn, 3,2);
        pawn = new Pawn(false);
        game.addPiece(pawn, 2,2);
        pawn = new Pawn(false);
        game.addPiece(pawn, 1,2);
        pawn = new Pawn(false);
        game.addPiece(pawn, 1,1);

        pawn = new Pawn(true);
        game.addPiece(pawn, 4,4);
        pawn = new Pawn(true);
        game.addPiece(pawn, 3,4);

        pawn = new Pawn(true);
        game.addPiece(pawn, 0,6);
        pawn = new Pawn(true);
        game.addPiece(pawn, 1,6);
        pawn = new Pawn(true);
        game.addPiece(pawn, 2,6);

        game.swapTurns();
        engine.returnBestMove(game, 5);

    }

    // 5 mins depth 6, fail
    // depth 4 gets to pawn promotion D8
    // need depth 6 to get answer
    @Test
    public void testPuzzle1700() {
        King king = new King(true);
        game.addPiece(king,3,7);
        king = new King(false);
        game.addPiece(king, 1,0);


        Queen queen = new Queen(true);
        game.addPiece(queen, 5,4);
        queen = new Queen(false);
        game.addPiece(queen, 1,2);

        Rook rook = new Rook(true);
        game.addPiece(rook, 0,7);
        rook = new Rook(false);
        game.addPiece(rook, 6,0);
        rook = new Rook(false);
        game.addPiece(rook, 2,1);


        Pawn pawn = new Pawn(false);
        game.addPiece(pawn, 0, 1);
        pawn = new Pawn(false);
        game.addPiece(pawn, 1, 1);
         pawn = new Pawn(false);
        game.addPiece(pawn, 2, 3);
        pawn = new Pawn(false);
        game.addPiece(pawn, 3, 4);
        pawn = new Pawn(false);
        game.addPiece(pawn, 5, 1);
        pawn = new Pawn(false);
        game.addPiece(pawn, 5, 3);
        pawn = new Pawn(false);
        game.addPiece(pawn, 7, 1);


        pawn = new Pawn(true);
        game.addPiece(pawn, 3, 1);
        pawn = new Pawn(true);
        game.addPiece(pawn, 3, 5);
        pawn = new Pawn(true);
        game.addPiece(pawn, 0, 6);
        pawn = new Pawn(true);
        game.addPiece(pawn, 1, 5);
        pawn = new Pawn(true);
        game.addPiece(pawn, 2, 6);
        pawn = new Pawn(true);
        game.addPiece(pawn, 7, 5);

        Knight knight = new Knight(true);
        game.addPiece(knight, 6, 5);

        Bishop bishop = new Bishop(true);
        game.addPiece(bishop,5,7);
        bishop = new Bishop(false);
        game.addPiece(bishop,2,2);

        bestMove = engine.returnBestMove(game, 6);
        assertEquals("Queen", bestMove.getValue0().getType());
        assertEquals(6,bestMove.getValue1());
        assertEquals(3, bestMove.getValue2());
    }



    @Test
    public void testPuzzle2050() {
        King king = new King(true);
        game.addPiece(king,6,6);
        king = new King(false);
        game.addPiece(king, 5,4);

        Queen queen = new Queen(true);
        game.addPiece(queen, 7, 3);

        Pawn pawn = new Pawn(true);
        game.addPiece(pawn, 4, 6);
        pawn = new Pawn(true);
        game.addPiece(pawn, 5, 5);
        pawn = new Pawn(false);
        game.addPiece(pawn, 2, 4);
        pawn = new Pawn(false);
        game.addPiece(pawn, 2, 3);

        Rook rook = new Rook(true);
        game.addPiece(rook, 5,7);
        rook = new Rook(true);
        game.addPiece(rook, 7,6);
        rook = new Rook(false);
        game.addPiece(rook, 5,0);
        rook = new Rook(false);
        game.addPiece(rook, 4,0);



        Knight knight = new Knight(true);
        game.addPiece(knight, 2, 7);
        knight = new Knight(true);
        game.addPiece(knight, 2, 1);

        Bishop bishop = new Bishop(true);
        game.addPiece(bishop,6,7);
        bishop = new Bishop(true);
        game.addPiece(bishop,7,1);
        bishop = new Bishop(false);
        game.addPiece(bishop,3,0);
        bishop = new Bishop(false);
        game.addPiece(bishop,6,0);

        bestMove = engine.returnBestMove(game, 6);
        assertEquals("Rook", bestMove.getValue0().getType());
        assertEquals(7,bestMove.getValue1());
        assertEquals(7, bestMove.getValue2());

    }
}
