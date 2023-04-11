package ui;

import model.Game;
import model.Piece;
import persistence.JsonWriter;
import persistence.Saves;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import static java.awt.BorderLayout.CENTER;

// In game component of GUI
public class GameGooey implements MouseListener, ActionListener {

    private JFrame frame = new JFrame("Game");
    private static Game g;
    private JPanel panel = new JPanel();
    private JButton[][] sq = new JButton[8][8];
    private int status = 0;
    private JLabel turn = new JLabel("White player's turn");

    private JPanel sidebar = new JPanel();
    JTextField typebar  = new JTextField("Enter name of save");

    private Piece movingpiece;


    // EFFECTS: Constructor for GameGooey
    public GameGooey(Game g) {
        this.g = g;
        panel.setLayout(new GridLayout(8, 8));
        frame.setLayout(new GridLayout(0, 2));
        setupBarPanel();
        sidebar.setLayout(new GridLayout(0,1));
        frame.add(sidebar);
        framePieces();
    }

    // MODIFIES: this
    // EFFECTS: sets up sidebar panel
    private void setupBarPanel() {
        sidebar.removeAll();
        sidebar.add(turn, CENTER);
        JButton button = new JButton("Save Game");
        button.addActionListener(this);
        sidebar.add(button);
        button = new JButton("Quit");
        button.addActionListener(this);
        sidebar.add(button);

        sidebar.validate();
        sidebar.repaint();
    }

    // MODIFIES: this
    // EFFECTS: refreshes label with current players turn:
    private void playerTurn() {
        if (g.getPlayer1turn()) {
            turn = new JLabel("White player's turn!");
        } else {
            turn = new JLabel("Black player's turn!");
        }
        setupBarPanel();
    }


    // EFFECTS: Returns type of piece is on square or empty if empty
    private String pieceType(int x, int y) {
        if (g.getBd().getPiece(x, y) != null) {
            return g.getBd().getPiece(x, y).getType();
        } else {
            return "EMPTY";
        }
    }

    // EFFECTS: Returns string to either white or black directory of Piece images
    private String whiteOrBlackImages(int x, int y) {
        if (g.getBd().getPiece(x, y) != null
                && g.getBd().getPiece(x, y).getWhite()) {
            return "./data/WhitePieceImages/";
        } else {
            return "./data/BlackPieceImages/";
        }
    }

    // EFFECTS: returns x position of square
    private int returnButtonXValue(JButton b) {
        int coordinate = -1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (sq[x][y] == b) {
                    coordinate = x;
                }
            }
        }
        return coordinate;
    }

    // EFFECTS: returns y position of square
    private int returnButtonYValue(JButton b) {
        int coordinate = -1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (sq[x][y] == b) {
                    coordinate = y;
                }
            }
        }
        return coordinate;
    }

    // MODIFIES: this
    // EFFECTS: creates buttons for the pieces on the board
    private void createButtons() {
        sq = new JButton[8][8];
        Boolean color = true;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ImageIcon b = new ImageIcon(whiteOrBlackImages(x, y) + pieceType(x, y) + ".png");
                JButton button = new JButton();
                button.setIcon(b);
                button.setSize(500, 500);

                if (color) {
                    button.setBackground(new java.awt.Color(138, 120, 93));
                    color = !color;
                } else {
                    button.setBackground(new java.awt.Color(252, 204, 116));
                    color = !color;
                }
                button.addMouseListener(this);


                sq[x][y] = button;
            }
        }
        addColorToButtons();
    }

    // MODIFIES: this
    // EFFECTS: Sets the background color of pieces
    private void addColorToButtons() {
        Boolean color = true;
        for (int x = 0; x < 8; x = x + 2) {
            for (int y = 0; y < 8; y++) {
                JButton button = sq[x][y];
                if (color) {
                    button.setBackground(new java.awt.Color(252, 204, 116));
                } else {
                    button.setBackground(new java.awt.Color(138, 120, 93));
                }
                color = ! color;
            }
        }
        for (int x = 1; x < 8; x = x + 2) {
            for (int y = 0; y < 8; y++) {
                JButton button = sq[x][y];
                if (!color) {
                    button.setBackground(new java.awt.Color(252, 204, 116));
                } else {
                    button.setBackground(new java.awt.Color(138, 120, 93));
                }
                color = ! color;
            }
        }

    }


    // MODIFIES: this
    // EFFECTS: Adds buttons to panel
    private void addButtonsToPanel() {

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                panel.add(sq[x][y]);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: Updates board reflecting current game state
    private void updateBoard() {
        panel.removeAll();

        createButtons();
        addButtonsToPanel();

        panel.revalidate();
        panel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Adds pieces to the frame
    public void framePieces() {

        createButtons();
        addButtonsToPanel();

        frame.add(panel, CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.pack();
        frame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: Processes the movement of pieces
    //          No effect if invalid move
    private void movePiece(JButton b) {
        int localstatus = status;
        int x = returnButtonXValue(b);
        int y = returnButtonYValue(b);
        Piece p = g.getBd().getPiece(x, y);
        if (localstatus == 0) {
            if (p != null
                    && g.isPieceTurnToMove(p)) {
                movingpiece = p;
                status = 1;
                b.setBorder(new EtchedBorder());
            }
        }
        if (localstatus == 1) {
            if (g.canBeMovedThere(movingpiece, x, y)) {
                g.movePiece(movingpiece, x, y);
                updateBoard();
                playerTurn();
            }
            status = 0;
        }
    }

    // EFFECTS: Displays a type bar
    private void typeBar() {

        sidebar.removeAll();
        typebar.addActionListener(this);
        sidebar.add(typebar);
        sidebar.validate();
        sidebar.repaint();

    }

    // EFFECTS: Saves current game state
    private void saveGame(String str) throws FileNotFoundException {

        JsonWriter writer = new JsonWriter("./data/" + str + ".json");
        Saves saves = new Saves();

        writer.open();
        writer.write(g);
        writer.close();

        try {
            saves.open();
            saves.addFile(str);
            saves.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unexpected Error Has Occurred");
        }
    }


    // EFFECTS: Processes movement of pieces
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent() != null) {
            movePiece((JButton) e.getComponent());
        }
    }

    // EFFECTS: Handles sidebar buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save Game")
                || e.getActionCommand().equals("Quit")) {

            if (e.getActionCommand().equals("Save Game")) {
                typeBar();
            }

            if (e.getActionCommand().equals("Quit")) {
                frame.dispose();
            }
        } else {
            try {
                saveGame(typebar.getText());
                setupBarPanel();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
                System.exit(3);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

