package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Pawn;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.LinkedList;
import java.util.List;

// Tests legal move generation for all pieces
public class TestMoveStrategy {
    Game game;
    List<Triplet<Piece, Integer , Integer>> legalMovesList;

    @BeforeEach
    public void setup() {
        game = new Game("default", null, true);
        legalMovesList = new LinkedList<>();
    }

    @Test
    public void testLegalMovesPawn() {
        Piece piece;
        for (int x = 0; x < 8; x++) {
            piece = game.getBd().getPiece(x, 1);
            piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
            assertTrue(legalMovesList.size() == (x + 1) * 2);
        }
        for (int x = 0; x < 8; x++) {
            piece = game.getBd().getPiece(x, 6);
            piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
            assertTrue(legalMovesList.size() == (x + 1) * 2 + 16);
        }
    }

    @Test
    public void testLegalMovesQueen() {
        // White queen initially starts with no moves available
        Piece piece = game.getBd().getPiece(3, 7);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertTrue(legalMovesList.size() == 0);

        // Moves white pawn blocking queen, two squares towards center
        game.movePiece(game.getBd().getPiece(3,6), 3, 4);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertEquals(legalMovesList.size(), 2);


        // Black queen initially starts with no moves available
        piece = game.getBd().getPiece(3, 0);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertTrue(legalMovesList.size() == 2);

        // Moves black pawn blocking queen, two squares towards center
        game.movePiece(game.getBd().getPiece(3,1), 3, 3);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertEquals(legalMovesList.size(), 4);
    }

    @Test
    public void testLegalMovesWhiteQueenDiagonal() {
        Piece piece = game.getBd().getPiece(3, 7);

        game.movePiece(game.getBd().getPiece(2,6), 2, 4);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertEquals(legalMovesList.size(), 3);

        legalMovesList.clear();
        game.movePiece(game.getBd().getPiece(4,6), 4, 4);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertEquals(legalMovesList.size(), 7);
    }

    @Test
    public void testLegalMovesBlackQueenDiagonal() {
        Piece piece = game.getBd().getPiece(3, 0);

        game.movePiece(game.getBd().getPiece(2,1), 2, 3);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertEquals(legalMovesList.size(), 3);

        legalMovesList.clear();
        game.movePiece(game.getBd().getPiece(4,1), 4, 3);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertEquals(legalMovesList.size(), 7);

        legalMovesList.clear();
        game.movePiece(game.getBd().getPiece(5, 1), 5, 2);
        piece.getMoveStrategy().legalMoves(game, piece, legalMovesList);
        assertEquals(legalMovesList.size(), 4);
    }

    @Test
    public void testLegalMovesKnight() {
        Piece piece = game.getBd().getPiece(1, 7);
        piece.getMoveStrategy().legalMoves(game,piece,legalMovesList);
        assertEquals(2, legalMovesList.size());

        piece = game.getBd().getPiece(6, 7);
        piece.getMoveStrategy().legalMoves(game,piece,legalMovesList);
        assertEquals(4, legalMovesList.size());
    }

    @Test
    public void testLegalMovesBishop() {
        Game gameEmpty = new Game("empty", null, true);
        Piece whiteBishop = new Bishop(true);
        King king = new King(true);
        Piece pawn1 = new Pawn(false);
        Piece pawn2 = new Pawn(false);
        Piece pawn3 = new Pawn(false);
        Piece pawn4 = new Pawn(false);
        gameEmpty.addPiece(king, 0,0);

        gameEmpty.addPiece(whiteBishop, 4, 4);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(12, legalMovesList.size());
        legalMovesList.clear();


        gameEmpty.addPiece(pawn1, 3,3);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(10, legalMovesList.size());
        legalMovesList.clear();

        gameEmpty.addPiece(pawn2, 5,3);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(8, legalMovesList.size());
        legalMovesList.clear();


        gameEmpty.addPiece(pawn2, 3,5);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(6, legalMovesList.size());
        legalMovesList.clear();


        gameEmpty.addPiece(pawn2, 5,5);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(4, legalMovesList.size());
    }


    @Test
    public void testLegalMovesBishop2() {
        Game gameEmpty = new Game("empty", null, true);
        Piece whiteBishop = new Bishop(true);
        King king = new King(true);
        Piece pawn1 = new Pawn(true);
        Piece pawn2 = new Pawn(true);
        Piece pawn3 = new Pawn(true);
        Piece pawn4 = new Pawn(true);
        gameEmpty.addPiece(king, 0,0);

        gameEmpty.addPiece(whiteBishop, 4, 4);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(12, legalMovesList.size());
        legalMovesList.clear();


        gameEmpty.addPiece(pawn1, 2,2);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(10, legalMovesList.size());
        legalMovesList.clear();

        gameEmpty.addPiece(pawn2, 6,2);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(8, legalMovesList.size());
        legalMovesList.clear();


        gameEmpty.addPiece(pawn2, 2,6);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(6, legalMovesList.size());
        legalMovesList.clear();


        gameEmpty.addPiece(pawn2, 6,6);
        whiteBishop.getMoveStrategy().legalMoves(gameEmpty, whiteBishop, legalMovesList);
        assertEquals(4, legalMovesList.size());
    }
}
