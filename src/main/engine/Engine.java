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

        List<Triplet<Piece, Integer, Integer>> legalMoves = validMoves(game);
        Triplet<Piece, Integer, Integer> optimalMove = null;

        if (game.getPlayer1turn()) { //WHITE
            int max = Integer.MIN_VALUE;
            for (Triplet<Piece, Integer, Integer> move: legalMoves) {
                if (move.getValue1() == 6 && move.getValue2() == 3) {
                    int y = 3;
                }
                //int result = miniMax(game, depth, move);
                int result = alphaBetaMiniMax(game, depth, move, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (result > max) {
                    System.out.println("Current best move white: " + move.getValue0().getType() + " " + move.getValue1() + move.getValue2() + " with value: " + result);
                    max = result;
                    optimalMove = move;
                }
            }
        } else { // BLACK
            int min = Integer.MAX_VALUE;
            for (Triplet<Piece, Integer, Integer> move: legalMoves) {
                //int result = miniMax(game, depth, move);
                int result = alphaBetaMiniMax(game, depth, move, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (result < min) {
                    System.out.println("Current best move black: " + move.getValue0().getType() + " " + move.getValue1() + move.getValue2() + " with value: " + result);
                    min = result;
                    optimalMove = move;
                }
            }
        }
        return optimalMove;
    }


    // EFFECTS: Return highest/lowest integer for given nextMove
    // alpha begins at -inf, White attempts to maximize it
    // beta begins at inf, Black attempts to minimize it
    // refer to README for explanation of alpha-beta pruning
    public int alphaBetaMiniMax(Game game, int depth, Triplet<Piece, Integer, Integer> nextMove, int alpha, int beta) {
        Piece piece = nextMove.getValue0();
        int originalX = piece.getXposition();
        int originalY = piece.getYposition();
        int nextX = nextMove.getValue1();
        int nextY = nextMove.getValue2();
        Boolean promotion = false;

        if (piece instanceof Pawn && ((nextY == 0 && piece.isWhite()) || (nextY == 7 && !piece.isWhite()))) {
            if (piece.isWhite()) {
                game.getPlayer1().getPieces().remove(piece);
            } else {
                game.getPlayer2().getPieces().remove(piece);
            }
            piece = new Queen(piece.isWhite());
            game.addPiece(piece, originalX, originalY);
            promotion = true;
        }

        Piece capturedPiece = movePiece(game, piece, nextX, nextY);

        int minimax;

        if (depth == 0) { // base cases need to include checkmates, run out of depth,
            minimax = evaluateGameState(game);
        } else if (capturedPiece instanceof King) {
            if (capturedPiece.isWhite()) {
                minimax = Integer.MIN_VALUE;
            } else {
                minimax = Integer.MAX_VALUE;
            }
        } else if (!piece.isWhite()) {
            minimax = alpha; // initially -inf
            for (Triplet<Piece, Integer, Integer> move : validMoves(game)) {
                int result = alphaBetaMiniMax(game, depth - 1, move, minimax, beta);
                if (result >= beta) {
                    minimax = result;
                    break;
                } else if (result > minimax) {
                    minimax = result;
                }
            }
        } else {
            minimax = beta; // initially inf
            for (Triplet<Piece, Integer, Integer> move : validMoves(game)) {
                int result = alphaBetaMiniMax(game, depth - 1, move, alpha, minimax);
                if (result <= alpha) {
                    minimax = result;
                    break;
                } else if (result < minimax) {
                    minimax = result;
                }
            }
        }
        if (promotion) {
            if (piece.isWhite()) {
                game.getPlayer1().getPieces().remove(piece);
            } else {
                game.getPlayer2().getPieces().remove(piece);
            }
            piece = new Pawn(piece.isWhite());
            game.addPiece(piece, nextX, nextY);
        }
        returnPiece(game, piece, capturedPiece, originalX, originalY, nextX, nextY);
        return minimax;
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

        if (depth == 0 ) { // base cases need to include checkmates, run out of depth,
            minimax = evaluateGameState(game);
        } else if (!piece.isWhite()) {
            minimax = -Integer.MIN_VALUE;
            for (Triplet<Piece, Integer, Integer> move : validMoves(game)) {
                int result = miniMax(game, depth - 1, move);
                if (result > minimax) {
                    minimax = result;
                }
            }
        } else {
            minimax = Integer.MAX_VALUE;
            for (Triplet<Piece, Integer, Integer> move : validMoves(game)) {
                int result = miniMax(game, depth - 1, move);
                if (result < minimax) {
                    minimax = result;
                }
            }
        }
        returnPiece(game, piece, capturedPiece, originalX, originalY, nextX, nextY);
        return minimax;
    }

    // EFFECTS: Moves piece to specific location and returns captured piece if there is one
    public Piece movePiece(Game game, Piece piece, int nextX, int nextY) {
        Piece capturedPiece;
        if (piece instanceof Pawn && piece.getXposition() != nextX && game.getBd().getPiece(nextX, nextY) == null) { //requirements for en-passant
            if (piece.isWhite()) {
                capturedPiece = game.getBd().getPiece(nextX, nextY + 1);
            } else {
                capturedPiece = game.getBd().getPiece(nextX, nextY - 1);
            }
        } else {
            capturedPiece = game.getBd().getPiece(nextX, nextY);
        }
        game.movePiece(piece, nextX, nextY);
        game.swapTurns();
        return capturedPiece;
    }

    // EFFECTS: Returns piece to its original position and restores capturedPiece if not null
    public void returnPiece(Game game, Piece movedPiece, Piece capturedPiece, int originalX, int originalY, int nextX, int nextY) {
        game.movePiece(movedPiece, originalX, originalY);
        if (capturedPiece != null) {
            if (capturedPiece instanceof Pawn) {
                game.addPiece(capturedPiece, capturedPiece.getXposition(), capturedPiece.getYposition());
            } else {
                game.addPiece(capturedPiece, nextX, nextY);
            }
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
        int gameValue = 0;
        for (Piece piece :game.getPlayer1().getPieces()) {
            gameValue += pieceToValue(piece);
        }
        for (Piece piece :game.getPlayer2().getPieces()) {
            gameValue -= pieceToValue(piece);
        }
        return gameValue;
    }


    // EFFECTS: Converts a piece to its relative value
    public int pieceToValue(Piece piece) {
        if (piece instanceof King) {
            return 10000;
        } else if (piece instanceof Queen) {
            return 9;
        } else if (piece instanceof Bishop) {
            return 3;
        } else if (piece instanceof Knight) {
            return 3;
        } else if (piece instanceof Rook) {
            return 5;
        } else if (piece instanceof Pawn) {
            return 1;
        } else {
            assert (false);
            return 0;
        }
    }
}
