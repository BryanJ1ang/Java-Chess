package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle1700 {
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
        System.out.println(engine.evaluateGameState(game));

        bestMove = engine.returnBestMove(game, 5);

    }

}


