package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Boolean white;
    private List<Piece> pieces = new ArrayList<Piece>();

    public Player(Boolean color) {
        white = color;
    }

    public void addPiece(Piece p) {
        pieces.add(p);
    }

    public Boolean getWhite() {
        return white;
    }

}
