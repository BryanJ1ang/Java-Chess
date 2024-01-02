package engine;

import javatuples.Triplet;
import model.Game;
import model.pieces.*;
import model.Player;

import java.util.LinkedList;
import java.util.List;

public class Engine {
    // 39
    int miniMax;
    int depth;

    // WHITE = MAX
    // BLACK = MIN
    // EFFECTS: Returns the best move for the given player's turn
    public Triplet<Piece, Integer, Integer> returnBestMove(Game game, int depth) {

        List<Triplet<Piece, Integer, Integer>> legalMoves = validMoves((game));
        Triplet<Piece, Integer, Integer> optimalMove = null;


        if (game.getPlayer1turn()) { //WHITE
            int max = -10000;
            for (Triplet<Piece, Integer, Integer> move: legalMoves) {
                int result = miniMax(game, depth, move);
                if (result > max) {
                    max = result;
                    optimalMove = move;
                }
            }
        } else { // BLACK
            int min = 10000;
            for (Triplet<Piece, Integer, Integer> move: legalMoves) {
                int result = miniMax(game, depth, move);
                if (result < min) {
                    min = result;
                    optimalMove = move;
                }
            }
        }
        return optimalMove;
    }

    // EFFECTS: Return highest/lowest integer for given nextMove
    public int miniMax(Game game, int depth, Triplet<Piece, Integer, Integer> nextMove) {
        Piece piece = nextMove.getValue0();
        int originalX = piece.getXposition();
        int originalY = piece.getYposition();
        int nextX = nextMove.getValue1();
        int nextY = nextMove.getValue2();
        Piece capturedPiece = movePiece(game, piece, nextX, nextY);
        int minimax;

        if (depth == 0 || capturedPiece instanceof King) { // base cases need to include checkmates, run out of depth,
            minimax = evaluateGameState(game);
        } else if (piece.isWhite()) {
            minimax = -10000;
            for (Triplet<Piece, Integer, Integer> move : validMoves(game)) {
                int result = miniMax(game, depth - 1, move);
                if (result > minimax) {
                    minimax = result;
                }
            }
        } else {
            minimax = 10000;
            for (Triplet<Piece, Integer, Integer> move : validMoves(game)) {
                int result = miniMax(game, depth - 1, move);
                if (result < minimax) {
                    minimax = result;
                }
            }
        }
        returnPiece(game, capturedPiece, originalX, originalY, nextX, nextY);
        return minimax;
    }

    // EFFECTS: Moves piece to specific location and returns captured piece if there is one
    public Piece movePiece(Game game, Piece piece, int nextX, int nextY) {
        Piece capturedPiece = game.getBd().getPiece(nextX, nextY);
        game.movePiece(piece, nextX, nextY);
        game.swapTurns();
        return capturedPiece;
    }

    public void returnPiece(Game game, Piece capturedPiece, int originalX, int originalY, int nextX, int nextY) {
        Piece piece = game.getBd().getPiece(nextX, nextY);
        game.movePiece(piece, originalX, originalY);
        if (capturedPiece != null) {
            game.addPiece(capturedPiece, nextX, nextY);
        }
        game.swapTurns();
    }

    // EFFECTS: Returns a list of valid moves for current player's turn
    public List<Triplet<Piece, Integer, Integer>> validMoves(Game game) {
        Player player;
        List<Triplet<Piece, Integer, Integer>> validMoves = new LinkedList<>();

        if (game.getPlayer1turn()) {
            player = game.getPlayer1();
        } else {
            player = game.getPlayer2();
        }
        for (Piece piece: player.getPieces()) {
            piece.getMoveStrategy().legalMoves(game, piece, validMoves);
        }
        return validMoves;
    }


    // EFFECTS: Evaluates the balance between White and Black in current game state
    //          More positive = white Greater advantage
    //          More negative = Black greater advantage
    //          Zero = Both sides balanced
    public int evaluateGameState(Game game) {
        int minimax = 0;
        for (Piece piece :game.getPlayer1().getPieces()) {
            minimax += pieceToValue(piece);
        }
        for (Piece piece :game.getPlayer2().getPieces()) {
            minimax -= pieceToValue(piece);
        }
        return minimax;
    }


    // EFFECTS: Converts a piece to its relative value
    public int pieceToValue(Piece piece) {
        if (piece instanceof King) {
            return 150;
        } else if (piece instanceof Queen) {
            return 9;
        } else if (piece instanceof Bishop) {
            return 3;   
        } else if (piece instanceof Knight) {
            return 3;
        } else if (piece instanceof Rook) {
            return 5;
        } else {
            return 1;
        }
    }
}
