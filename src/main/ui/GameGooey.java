package ui;

import model.Game;
import model.Board;
import model.Piece;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;

public class GameGooey implements ActionListener {

    private static Game g;
    private ImageIcon[][] squares = new ImageIcon[8][8];
    private MovePieceListener pieceListener = new MovePieceListener();

    // EFFECTS: Constructor for GameGooey
    public GameGooey(Game g) {
        this.g = g;
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
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ImageIcon b = new ImageIcon(whiteOrBlackImages(x, y) + PieceType(x,y) + ".png");
                squares[x][y] = b;
            }
        }
    }

    public JFrame returnGameGooeyFrame() {
        JFrame jframe = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));

        createSquareImages();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                JLabel label = new JLabel(squares[x][y]);
                label.addMouseListener(pieceListener);
                label.addMouseMotionListener(pieceListener);
                panel.add(label);
            }
        }

        jframe.add(panel, CENTER);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("Chess");
        jframe.pack();
        jframe.setVisible(true);
        return jframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
