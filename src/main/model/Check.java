package model;

import model.Pieces.King;
import model.Pieces.Piece;

import java.util.List;

// Class representing check/mate
public class Check {
    private static Check check;
    Game game;
    King king1; // white king
    King king2; // black king

    // EFFECTS: Constructor for checkmate
    public Check(Game g) {
        game = g;
        for (Piece p : g.getPlayer1().getPieces()) {
            if (p instanceof King) {
                king1 = (King) p;
            }
        }

        for (Piece p : g.getPlayer2().getPieces()) {
            if (p instanceof King) {
                king2 = (King) p;
            }
        }
    }

    // EFFECTS: return true if one side is checkmated
    //          (only one side can be in check mate at any given time)
    public Boolean isCheckMate() {
        return ((whiteCheck() && whiteCheckMate()) || (blackCheckMate() && blackCheck()));
    }

    // EFFECTS: true if moving player of given piece is in check after moving to (x,y)
    public Boolean moveIntoCheck(Piece p, int x, int y) {
        Piece piece = null;
        int x1 = p.getXposition();
        int y1 = p.getYposition();
        if (game.getBd().getPiece(x,y) != null) {
            piece = game.getBd().getPiece(x,y);
        }

        game.getBd().movePiece(p, x, y);
        if (p.getWhite()) {
            Boolean b = whiteCheck();
            game.getBd().movePiece(p, x1, y1);
            if (piece != null) {
                piece.setPositions(x,y);
                game.getBd().addPiece(piece,x,y);
            }
            return b;
        } else {
            Boolean b = blackCheck();
            game.getBd().movePiece(p, x1, y1);
            if (piece != null) {
                piece.setPositions(x,y);
                game.getBd().addPiece(piece,x,y);
            }
            return b;
        }
    }

    // REQUIRES: White is in check
    // EFFECTS: return true if no valid moves for white/player1
    private Boolean whiteCheckMate() {
        List<Piece> pieces = game.getPlayer1().getPieces();
        Game temp = game;
        for (Piece p : pieces) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    System.out.println(p.getType());
                    if (temp.validMove(p, x, y)
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
            if (p instanceof King) {
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
            if (p instanceof King) {
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
