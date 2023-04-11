package model;

import org.json.JSONArray;
import org.json.JSONObject;

// represents a game with players and a board
public class Game {
    private Board bd = new Board();
    private Player player1 = new Player(true); //white
    private Player player2 = new Player(false); //black
    private Boolean player1turn = true;
    private Boolean player2turn = false;
    private Boolean gamestatus = true;


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

    // MODIFIES: this
    // EFFECTS: changes turns
    public void changeTurn() {
        player1turn = !player1turn;
        player2turn = !player2turn;
    }

    // MODIFIES: THIS
    //EFFECTS: moves piece to given location and switches turns
    public void movePiece(Piece p, int nextx, int nexty) {
        if (bd.getPiece(nextx,nexty) != null ) {
            logCapturesPiece(p, bd.getPiece(nextx,nexty));
        } else {
            logMovedPiece(p, nextx, nexty);
        }
        bd.movePiece(p, nextx, nexty);
        player1turn = !player1turn;
        player2turn = !player2turn;
    }


    // EFFECTS: returns color of piece
    private String colorOfPiece(Piece p) {
        if (p.white) {
            return "White";
        } else {
            return "Black";
        }
    }

    // MODIFIES: EventLog
    // EFFECTS: logs new game created
    private void logNewGameCreated() {
        String message = "New game created";
        Event event = new Event(message);
        EventLog.getInstance().logEvent(event);
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

    //EFFECTS: converts to Y index to chess position
    private String indexToChessIndex(int y) {
        return Integer.toString(8-y);
    }


        // EFFECTS: Converts number to corresponding ordered alphabet character
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
        return (!p.white && player2turn) || (p.white && player1turn);
    }

    // EFFECTS: returns true if piece can be moved specified location
    public Boolean canBeMovedThere(Piece piece, int nextx, int nexty) {
        return bd.validMove(piece, nextx, nexty);
    }


    // MODIFIES: this, player
    // EFFECTS: adds a piece to the board
    public void addPiece(Piece p, int x, int y) {
        bd.addPiece(p, x, y);
        if (p.white) {
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

    // EFFECTS: Changes the game state to be over
    public void gameOver() {
        gamestatus = false;
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

    public Boolean getGamestatus() {
        return gamestatus;
    }
}


;