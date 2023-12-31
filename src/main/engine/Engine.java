package engine;

import Tuple.Triplet;
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

        if (depth == 0) {
            minimax = evaluateGameState(game);
        } else if (piece.getWhite()) {
            minimax = -10000;
            game.swapTurns();
            for (Triplet<Piece, Integer, Integer> move: validMoves(game)) {
                int result = miniMax(game, depth - 1, move);
                if (result > minimax) {
                    minimax = result;
                }
            }
        } else {
            minimax = 10000;
            game.swapTurns();
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
            piece.
            legalMoves(game, piece, validMoves);
        }
        return validMoves;
    }

    // REQUIRES: piece is actual type Queen or Bishop
    // EFFECTS: Adds legal diagonal moves to list
    public void diagonalMoves(Game game, Piece piece, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = piece.getXposition();
        int posY = piece.getYposition();
        int y = posY;
        for (int x = posX + 1; x < 8; x++) {
            y += 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>((piece,x,y));
            } else {
                break;
            }
        }
         y = posY;
        for (int x = posX + 1; x < 8; x++) {
            y -= 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>( piece,x,y));
            } else {
                break;
            }
        }
         y = posY;
        for (int x = posX - 1; x > -1; x--) {
            y += 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>( piece,x,y));
            } else {
                break;
            }
        }
         y = posY;
        for (int x = posX - 1; x > -1; x--) {
            y -= 1;
            if (game.validMove(piece, x, y)) {
                list.add(new Triplet<>( piece,x,y));
            } else {
                break;
            }
        }
    }


    // REQUIRES: Piece is actual type Queen or Rook
    // EFFECTS: Adds legal up/down/left/right moves to list
    public void perpendicularMoves(Game game, Piece piece, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = piece.getXposition();
        int posY = piece.getYposition();
        for (int x = posX; x < 8; x++) {
            if (game.validMove(piece, x, posY)) {
                list.add(new Triplet<>((Piece) piece, x, posY));
            } else {
                break;
            }
        }
        for (int y = posY; y < 8; y++) {
            if (game.validMove(piece, posX, y)) {
                list.add(new Triplet<>((Piece) piece, posX, y));
            } else {
                break;
            }
        }
        for (int x = posX; x > -1; x--) {
            if (game.validMove(piece, x, posY)) {
                list.add(new Triplet<>((Piece) piece, x, posY));
            } else {
                break;
            }
        }
        for (int y = posY; y > -1; y--) {
            if (game.validMove(piece, posX, y)) {
                list.add(new Triplet<>((Piece) piece, posX, y));
            } else {
                break;
            }
        }
    }

    public void legalMoves(Game game, Bishop bishop, List<Triplet<Piece, Integer, Integer>> list) {
        diagonalMoves(game, bishop, list);
    }

    public void legalMoves(Game game, Knight knight, List<Triplet<Piece, Integer, Integer>> list) {
        int posX = knight.getXposition();
        int posY = knight.getYposition();
        if (game.validMove(knight, posX + 2, posY + 1)) {
            list.add(new Triplet<>((Piece) knight, posX + 2, posY + 1));
        }
        if (game.validMove(knight, posX + 2, posY - 1)) {
            list.add(new Triplet<>((Piece) knight, posX + 2, posY - 1));
        }
        if (game.validMove(knight, posX - 2, posY + 1)) {
            list.add(new Triplet<>((Piece) knight, posX - 2, posY + 1));
        }
        if (game.validMove(knight, posX - 2, posY - 1)) {
            list.add(new Triplet<>((Piece) knight, posX - 2, posY - 1));
        }
        if (game.validMove(knight, posX + 1, posY + 2)) {
            list.add(new Triplet<>((Piece) knight, posX + 1, posY + 2));
        }
        if (game.validMove(knight, posX + 1, posY - 2)) {
            list.add(new Triplet<>((Piece) knight, posX + 1, posY - 2));
        }
        if (game.validMove(knight, posX - 1, posY + 2)) {
            list.add(new Triplet<>((Piece) knight, posX - 1, posY + 2));
        }
        if (game.validMove(knight, posX - 1, posY - 2)) {
            list.add(new Triplet<>((Piece) knight, posX - 1, posY - 2));
        }
    }

    public void legalMoves(Game game, Queen queen, List<Triplet<Piece, Integer, Integer>> list) {
        diagonalMoves(game, queen, list);
        perpendicularMoves(game, queen, list);
    }

    public void legalMoves(Game game, Pawn pawn, List<Triplet<Piece, Integer, Integer>> list) {

    }

    public void legalMoves(Game game, Rook rook, List<Triplet<Piece, Integer, Integer>> list) {
        perpendicularMoves(game, rook, list);
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
            return 39;
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
