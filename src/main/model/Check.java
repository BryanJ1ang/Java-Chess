package model;

import model.pieces.King;
import model.pieces.Pawn;
import model.pieces.Piece;

import java.util.List;

// Class representing check/mate
public class Check {
    Game game;
    King king1; // white king
    King king2; // black king

    // EFFECTS: Constructor for checkmate
    public Check(Game g) {
        game = g;
        for (Piece p : g.getPlayer1().getPieces()) {
            if (p instanceof King) {
                king1 = (King) p;
                break;
            }
        }

        for (Piece p : g.getPlayer2().getPieces()) {
            if (p instanceof King) {
                king2 = (King) p;
                break;
            }
        }
    }

    // EFFECTS: return true if one side is checkmated
    //          (only one side can be in check mate at any given time)
    public Boolean isCheckMate() {
        return ((whiteCheck() && whiteCheckMate()) || (blackCheckMate() && blackCheck()));
    }

    // EFFECTS: true if moving player of given piece is in check after moving to (x,y)
    public Boolean moveIntoCheck(Piece piece, int nextX, int nextY) {
        Piece capturedPiece = null;
        int originalX = piece.getXposition();
        int originalY = piece.getYposition();
        boolean check;
        boolean enpassant = false;

        if (game.getBd().getPiece(nextX,nextY) != null) {
            capturedPiece = game.getBd().getPiece(nextX, nextY);
            if (!(capturedPiece instanceof King)) {
                capturedPiece.setPositions(-1,-1);
            }
        } else if (piece instanceof Pawn) {
            //stub for retrieving enpassant
        }

        game.getBd().movePiece(piece, nextX, nextY);
        if (piece.isWhite()) {
            check = whiteCheck();
        } else {
            check = blackCheck();
        }
        game.getBd().movePiece(piece, originalX, originalY);
        if (capturedPiece != null) {
            game.getBd().addPiece(capturedPiece, nextX, nextY);
            if (!(capturedPiece instanceof King)) {
                capturedPiece.setPositions(nextX, nextY);
            }
        }
        return check;
    }

    // REQUIRES: White is in check
    // EFFECTS: return true if no valid moves for white/player1
    private Boolean whiteCheckMate() {
        List<Piece> pieces = game.getPlayer1().getPieces();
        for (Piece p : pieces) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    System.out.println(p.getType());
                    if (game.validMove(p, x, y)
                            && !(game.getBd().getPiece(x,y) == p)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // REQUIRES: Black is in check
    // EFFECTS: return true if no valid moves for black/player2
    private Boolean blackCheckMate() {
            List<Piece> pieces = game.getPlayer2().getPieces();
        for (Piece p : pieces) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (game.validMove(p, x, y)
                            && !(game.getBd().getPiece(x,y) == p)) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    // EFFECTS: Returns true if white king is in check
    public Boolean whiteCheck() {
        List<Piece> pieces = game.getPlayer2().getPieces();
        for (Piece p : pieces) {
            if (p instanceof King || p.removed()) {
                continue;
            }
            int x = p.getXposition();
            int y = p.getYposition();
            if (game.validMove(p, king1.getXposition(), king1.getYposition())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns true if black king is in check
    public Boolean blackCheck() {
        List<Piece> pieces = game.getPlayer1().getPieces();
        for (Piece p : pieces) {
            if (p instanceof King || p.removed()) {
                continue;
            }
            int x = p.getXposition();
            int y = p.getYposition();
            if (game.validMove(p, king2.getXposition(), king2.getYposition())) {
                return true;
            }
        }
        return false;
    }


}
