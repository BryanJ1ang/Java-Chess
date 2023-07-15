package model;

public interface EnPassant {
    Boolean enPassant = false; // true if this piece vulnerable to en passant
    Game game = null;

    public Boolean canEnPassant(Game g, int x, int y);

    public Boolean getEnPassant();
}
