package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle2050 {

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

        bestMove = engine.returnBestMove(game, 2);
        assertEquals("Rook", bestMove.getValue0().getType());
        assertEquals(7,bestMove.getValue1());
        assertEquals(7, bestMove.getValue2());
    }
}
