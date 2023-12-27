package model;

import model.Pieces.*;
import org.json.JSONArray;
import org.json.JSONObject;


// represents a game with players and a board
public class Game {
    private Board bd = new Board();
    private Player player1 = new Player(true); //white
    private Player player2 = new Player(false); //black
    private Boolean player1turn = true;
    private Boolean player2turn = false;
    private int gamestatus = 0;
    private Boolean checkmate;
    private Boolean whiteCheck;
    private Boolean blackCheck;

    //EFFECTS: Constructor for game
    public Game(String str, Board b, Boolean turn) {
        if (str.equals("default")) {
            defaultSetUp();
        }

        if (b != null) {
            bd = b;
        }
        if (turn) {
            player1turn = true;
            player2turn = false;
        } else {
            player1turn = false;
            player2turn = true;
        }

        logNewGameCreated();
    }

    // MODIFIES: Board
    // EFFECTS: Moves piece to given position
    public void movePiece(Piece p, int x, int y) {
        if (p instanceof Pawn) { // En_Passant Move
            int originalX = p.getXposition();
            if ((originalX == x + 1 || originalX == x - 1) && bd.getPiece(x,y) == null) {
                enPassant((Pawn) p, x, y);
            }
        }

        if (bd.getPiece(x,y) != null) { // Captures if there's a piece
            Piece capturedPiece = bd.getPiece(x,y);
            if (capturedPiece.getWhite()) {
                player1.removePiece(capturedPiece);
            } else {
                player2.removePiece(capturedPiece);
            }
        }

        if (p instanceof King) { // Castles if able to
            Castle castle = new Castle(this);
            if (castle.canCastle((King) p, x, y)) {
                castle.castle(x,y);
            }
        }

        bd.movePiece(p, x, y);
        if (p instanceof Pawn && !p.getMoved()) { // Unmark all pawns for EnPassant
            Pawn pawn = (Pawn) p;
            pawn.markEnPassant();
            pawn.setMoved();
        }
        p.setMoved();
    }

    // MODIFIES: this
    // EFFECTS: Changes turns
    public void swapTurns() {
        player1turn = !player1turn;
        player2turn = !player2turn;
    }

    // EFFECTS: Returns true if it is a legal en-passant move
    public Boolean canEnPassant(Pawn pawn, int nextx, int nexty) {
        if (pawn.canEnPassant(this, nextx, nexty)) {
            Check check = new Check(this);
            if (!check.moveIntoCheck(pawn, nextx, nexty)) {
                return true;
            }
        }
        return false;
    }

    public void enPassant(Pawn pawn, int nextx, int nexty) {
        if (pawn.getWhite()) {
            player2.removePiece(this.getBd().getPiece(nextx, nexty + 1));

        } else {
            player1.removePiece(this.getBd().getPiece(nextx, nexty - 1));
        }
        pawn.EnPassant(nextx, nexty);
    }

    // EFFECTS: returns true if piece can be moved to specified location
    public Boolean validMove(Piece piece, int nextx, int nexty) {
        Game temp = this;
        if (piece instanceof Pawn) {
            Pawn pawn = (Pawn) piece;
            if (canEnPassant(pawn, nextx, nexty)) {
                return true;
            }
        }

        if (piece instanceof King) {
            King k = (King) piece;
            Castle castle = new Castle(this);
            if (castle.canCastle(k, nextx, nexty)) {
                return true;
            }
        }

        if (!bd.validMove(piece, nextx, nexty)) {
            return false;
        } else {
            Check check = new Check(this);
            return !check.moveIntoCheck(piece, nextx, nexty);
        }
    }

    // EFFECTS: returns color of piece
    private String colorOfPiece(Piece p) {
        if (p.getWhite()) {
            return "White";
        } else {
            return "Black";
        }
    }

    // EFFECT: continuegame then gamestatus = 0
    //         if white checkmate gamestatus = 1,
    //         if black checkmate gamestatus = 2.
    //
    public void updateGameStatus() {
        Check check = new Check(this);
        if (check.isCheckMate()) {
            if (check.whiteCheck()) {
                gamestatus = 1;
            } else {
                gamestatus = 2;
            }
        }
    }

    // MODIFIES: EventLog
    // EFFECTS: logs new game created
    private void logNewGameCreated() {
        String message = "New game created";
        Event event = new Event(message);
        EventLog.getInstance().logEvent(event);
    }

    // EFFECTS: Promotes pawn to new piece
    public void promotePawn(Piece piece, String type) {
        int x = piece.getXposition();
        if (piece.getWhite()) {
            Piecelibrary library = new Piecelibrary();
            Piece p = library.retrievePieceFromLibrary(type, "WHITE");
            bd.removePiece(piece.getXposition(), piece.getYposition());
            addPiece(p, x, 0);
            p.setPositions(x, 0);
        } else {
            Piecelibrary library = new Piecelibrary();
            Piece p = library.retrievePieceFromLibrary(type, "BLACK");
            bd.removePiece(piece.getXposition(), piece.getYposition());
            addPiece(p, x, 7);
            p.setPositions(x, 7);
        }
    }

    // MODIFIES: EventLog
    // EFFECTS: logs a piece captured by another
    private void logCapturesPiece(Piece moving, Piece captured) {
        String from = indexToChessCoordinate(moving.getXposition(), moving.getYposition());
        String to = indexToChessCoordinate(captured.getXposition(), captured.getYposition());

        String message = colorOfPiece(moving) + " " + moving.getType().toLowerCase() + " captures "
                + colorOfPiece(captured).toLowerCase() + " " + captured.getType().toLowerCase() + " from "
                + from + " to " + to;

        Event event = new Event(message);
        EventLog.getInstance().logEvent(event);
    }

    // EFFECTS: Converts indexes to corresponding chess coordinate
    private String indexToChessCoordinate(int x, int y) {
        char letter = convertNumberToAlphabet(x);
        String number = indexToChessIndex(y);
        return String.valueOf(letter) + number;
    }

    //EFFECTS: converts Y index to chess position
    private String indexToChessIndex(int y) {
        return Integer.toString(8 - y);
    }


        // EFFECTS: Converts number to corresponding ordered alphabet character A-H
    public static char convertNumberToAlphabet(int i) {
        return (char) (i + 65);
    }


    // MODIFIES: EventLog
    // EFFECTS: logs the moved piece
    private void logMovedPiece(Piece piece, int nextx, int nexty) {
        String from = indexToChessCoordinate(piece.getXposition(), piece.getYposition());
        String to = indexToChessCoordinate(nextx, nexty);

        String message = colorOfPiece(piece) + " " + piece.getType().toLowerCase()
                + " moved from " + from + " to " + to;

        Event event = new Event(message);
        EventLog.getInstance().logEvent(event);
    }

   //EFFECTS: returns true if given piece can be moved on current player's turn
    public Boolean isPieceTurnToMove(Piece p) {
        return (!p.getWhite() && player2turn) || (p.getWhite() && player1turn);
    }



    // MODIFIES: this, player
    // EFFECTS: adds a piece to the board belonging to respective payer
    public void addPiece(Piece p, int x, int y) {
        bd.addPiece(p, x, y);
        if (p.getWhite()) {
            player1.addPiece(p);
        } else {
            player2.addPiece(p);
        }
    }

    //MODIFIES: this, board
    //EFFECTS: sets up the board with all 32 pieces.
    public void defaultSetUp() {
        addWhitePawns();
        addBlackPawns();
        addDefaultBackRowBlack();
        addDefaultBackRowWhite();
    }

    // MODIFIES: this, board
    // EFFECTS: adds row of pawns for White
    private void addWhitePawns() {
        Pawn p1 = new Pawn("White");
        Pawn p2 = new Pawn("White");
        Pawn p3 = new Pawn("White");
        Pawn p4 = new Pawn("White");
        Pawn p5 = new Pawn("White");
        Pawn p6 = new Pawn("White");
        Pawn p7 = new Pawn("White");
        Pawn p8 = new Pawn("White");
        addPiece(p1, 0, 6);
        addPiece(p2, 1, 6);
        addPiece(p3, 2, 6);
        addPiece(p4, 3, 6);
        addPiece(p5, 4, 6);
        addPiece(p6, 5, 6);
        addPiece(p7, 6, 6);
        addPiece(p8, 7, 6);
    }

    // MODIFIES: this, board
    // EFFECTS: adds row of pawns for Black
    private void addBlackPawns() {
        Pawn p9 = new Pawn("Black");
        Pawn p10 = new Pawn("Black");
        Pawn p11 = new Pawn("Black");
        Pawn p12 = new Pawn("Black");
        Pawn p13 = new Pawn("Black");
        Pawn p14 = new Pawn("Black");
        Pawn p15 = new Pawn("Black");
        Pawn p16 = new Pawn("Black");
        addPiece(p9, 0, 1);
        addPiece(p10, 1, 1);
        addPiece(p11, 2, 1);
        addPiece(p12, 3, 1);
        addPiece(p13, 4, 1);
        addPiece(p14, 5, 1);
        addPiece(p15, 6, 1);
        addPiece(p16, 7, 1);
    }

    // MODIFIES: this, board
    // EFFECTS: adds backrow pieces for white
    private void addDefaultBackRowWhite() {
        Bishop b1 = new Bishop("White");
        Bishop b2 = new Bishop("White");
        Knight kn1 = new Knight("White");
        Knight kn2 = new Knight("White");
        Rook r1 = new Rook("White");
        Rook r2 = new Rook("White");
        King k1 = new King("White");
        Queen q1 = new Queen("White");
        addPiece(kn1, 1, 7);
        addPiece(kn2, 6, 7);
        addPiece(b1, 2, 7);
        addPiece(b2, 5, 7);
        addPiece(r1, 0, 7);
        addPiece(r2, 7, 7);
        addPiece(q1, 3, 7);
        addPiece(k1, 4, 7);
    }

    // MODIFIES: this, board
    // EFFECTS: adds backrow pieces for black
    private void addDefaultBackRowBlack() {
        King k2 = new King("Black");
        Queen q2 = new Queen("Black");
        Rook r3 = new Rook("Black");
        Rook r4 = new Rook("Black");
        Bishop b3 = new Bishop("Black");
        Bishop b4 = new Bishop("Black");
        Knight kn3 = new Knight("Black");
        Knight kn4 = new Knight("Black");
        addPiece(kn3, 1, 0);
        addPiece(kn4, 6, 0);
        addPiece(b3, 2, 0);
        addPiece(b4, 5, 0);
        addPiece(r3, 0, 0);
        addPiece(r4, 7, 0);
        addPiece(q2, 3, 0);
        addPiece(k2, 4, 0);
    }


    // EFFECTS: Parses pieces on the board to Json
    private JSONArray piecesToJson() {
        JSONArray jsonarray = new JSONArray();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (null == bd.getPiece(x, y)) {
                    jsonarray.put("null");
                } else {
                    jsonarray.put(bd.getPiece(x, y).getType().toUpperCase());
                }
            }
        }
        return jsonarray;
    }

    // EFFECTS: Parses colors of the pieces on the board to Json
    private JSONArray coloursToJson() {
        JSONArray jsonarray = new JSONArray();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (null == bd.getPiece(x,y)) {
                    jsonarray.put("null");
                } else {
                    if (bd.getPiece(x,y).getWhite()) {
                        jsonarray.put("WHITE");
                    } else {
                        jsonarray.put("BLACK");
                    }
                }
            }
        }
        return jsonarray;
    }

    // EFFECTS: Parses game into Json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray pieces = piecesToJson();
        JSONArray colours = coloursToJson();

        json.put("player1", player1);
        json.put("player2", player2);
        json.put("player1turn", player1turn);
        json.put("pieces", pieces);
        json.put("colours", colours);
        return json;
    }


    public Board getBd() {
        return bd;
    }

    public Boolean getPlayer1turn() {
        return player1turn;
    }

    public Boolean getPlayer2turn() {
        return player2turn;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getGamestatus() {
        return gamestatus;
    }



}


;