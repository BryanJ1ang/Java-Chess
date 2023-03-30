package ui;

import model.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;

public class GameGooey implements ActionListener {

    private JFrame frame = new JFrame();
    private static Game g;
    private MovePieceListener pieceListener = new MovePieceListener();
    private JPanel[][] squares = new JPanel[8][8];
    private JButton[][] buttons = new JButton[8][8];

    // EFFECTS: Constructor for GameGooey
    public GameGooey(Game g) {
        this.g = g;
        createSquareImages();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.pack();
        frame.setVisible(true);
    }


    private String PieceType(int x, int y) {
            if (g.getBd().getPiece(x,y) != null) {
                return g.getBd().getPiece(x,y).getType();
            } else {
                return "EMPTY";
            }
    }

    private String whiteOrBlackImages(int x, int y) {
        if (g.getBd().getPiece(x,y) != null
                && g.getBd().getPiece(x,y).getWhite()) {
            return "./data/WhitePieceImages/";
        } else {
            return "./data/BlackPieceImages/";
        }
    }

    private void createSquareImages() {
        frame.setLayout(new GridLayout(8, 8));
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ImageIcon b = new ImageIcon(whiteOrBlackImages(x, y) + PieceType(x,y) + ".png");
                JButton button = new JButton();
                button.setIcon(b);

                buttons[x][y] = button;
                frame.add(button);
            }
        }
    }

    private void createBoard() {
        frame.setLayout(new GridLayout(8,8));
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
            }
        }
    }

    // EFFECTS: Adds pieces to the frame
    public void framePieces() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));

        createSquareImages();

        frame.add(panel, CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.pack();
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JPanel[][] getSquares() {
        return squares;
    }
}
