package ui;

import model.Game;
import model.pieces.Pawn;
import model.pieces.Piece;
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

    private JFrame mainFrame = new JFrame("Game");
    private static Game g;
    private JPanel buttonPanel = new JPanel();
    private JButton[][] sq = new JButton[8][8];
    private JLabel turnLabel = new JLabel("White player's turn");
    private JPanel sidebar = new JPanel();
    private JTextField typebar  = new JTextField("Enter name of save");
    private Piece selectedPiece;

    // EFFECTS: Constructor for GameGooey
    public GameGooey(Game g) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 50;
        c.gridy = 0;
        c.gridheight = 50;
        this.g = g;
        buttonPanel.setLayout(new GridLayout(8, 8));
        mainFrame.setLayout(new GridBagLayout());
        setupBarPanel();
        sidebar.setLayout(new GridLayout(0,1));
        mainFrame.add(sidebar, c);
        framePieces();
    }

    // MODIFIES: this
    // EFFECTS: sets up sidebar panel
    private void setupBarPanel() {
        sidebar.removeAll();
        sidebar.add(turnLabel, CENTER);
        JButton button = new JButton("Save Game");
        button.addActionListener(this);
        button.setSize(new Dimension(50, 50));
        sidebar.add(button);
        button = new JButton("Quit");
        button.addActionListener(this);
        button.setSize(new Dimension(50, 50));
        sidebar.add(button);

        sidebar.validate();
        sidebar.repaint();
    }

    // MODIFIES: this
    // EFFECTS: refreshes label with current players turn:
    private void playerTurn() {
        if (g.getPlayer1turn()) {
            turnLabel = new JLabel("White player's turn!");
        } else {
            turnLabel = new JLabel("Black player's turn!");
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
                && g.getBd().getPiece(x, y).isWhite()) {
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
                buttonPanel.add(sq[x][y]);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: Updates board reflecting current game state
    private void updateBoard() {
        buttonPanel.removeAll();

        createButtons();
        addButtonsToPanel();
        g.updateGameStatus();
        if (g.getGamestatus() == 1 || g.getGamestatus() == 2) {
            sidebar.removeAll();
            sidebar = gameOverPanel();
            sidebar.revalidate();
            sidebar.repaint();
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private JPanel gameOverPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("GAME OVER!");
        button.setBackground(new Color(150, 130, 100));
        panel.add(button);
        return panel;
    }

    private JFrame gameOverFrame() {
        JFrame frame = new JFrame();
        JButton button = new JButton("GAME OVER");
        button.setSize(new Dimension(500, 500));
        frame.add(button);
        return frame;
    }

    // MODIFIES: this
    // EFFECTS: Adds pieces to the frame
    public void framePieces() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 50;
        c.gridwidth = 50;

        createButtons();
        addButtonsToPanel();

        mainFrame.add(buttonPanel, c);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Chess");
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Processes user selection/movement of pieces
    //          No effect if invalid move
    private void movePiece(JButton b) {
        int x = returnButtonXValue(b);
        int y = returnButtonYValue(b);
        Piece p = g.getBd().getPiece(x, y);
        if (p != null && g.isPieceTurnToMove(p)) {
            selectedPiece = p;
            b.setBorder(new EtchedBorder());
        } else if (selectedPiece != null && g.validMove(selectedPiece, x, y)) {
            Pawn.updatePiece();
            g.movePiece(selectedPiece, x, y);
            if (selectedPiece instanceof Pawn) {
                Pawn pawn = (Pawn) selectedPiece;
                if (pawn.canPromote()) {
                    pawnPromotion(pawn);
                }
            }
            g.swapTurns();
            g.updateGameStatus();
            updateBoard();
            playerTurn();
            selectedPiece = null;

        }
    }

    // EFFECTS: Promotes pawn
    private void pawnPromotion(Pawn pawn) {
        g.promotePawn(pawn, "QUEEN");
    }


    // EFFECT: returns promotion panel
    private JPanel displayPromotion(Boolean colour) {
        JPanel panel = new JPanel();
        ImageIcon icon;
        JButton button;
        if (colour) {
            return displayWhitePromotion();
        } else {
            return displayBlackPromotion();
        }
    }

    private JPanel displayBlackPromotion() {
        JPanel panel = new JPanel();
        ImageIcon icon;
        JButton button;

        button = new JButton("QUEEN");
        icon = new ImageIcon("./data/BlackPieceImages/QUEEN.png");
        button.setIcon(icon);
        panel.add(button);

        button = new JButton("ROOK");
        icon = new ImageIcon("./data/BlackPieceImages/ROOK.png");
        button.setIcon(icon);
        panel.add(button);

        button = new JButton("BISHOP");
        icon = new ImageIcon("./data/BlackPieceImages/BISHOP.png");
        button.setIcon(icon);
        panel.add(button);

        button = new JButton("KNIGHT");
        icon = new ImageIcon("./data/BlackPieceImages/KNIGHT.png");
        button.setIcon(icon);
        panel.add(button);

        return panel;
    }

    private JPanel displayWhitePromotion() {
        JPanel panel = new JPanel();
        ImageIcon icon;
        JButton button;

        button = new JButton("QUEEN");
        icon = new ImageIcon("./data/WhitePieceImages/QUEEN.png");
        button.setIcon(icon);
        panel.add(button);

        button = new JButton("ROOK");
        icon = new ImageIcon("./data/WhitePieceImages/ROOK.png");
        button.setIcon(icon);
        panel.add(button);

        button = new JButton("BISHOP");
        icon = new ImageIcon("./data/WhitePieceImages/BISHOP.png");
        button.setIcon(icon);
        panel.add(button);

        button = new JButton("KNIGHT");
        icon = new ImageIcon("./data/WhitePieceImages/KNIGHT.png");
        button.setIcon(icon);
        panel.add(button);

        return panel;
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
                mainFrame.dispose();
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


    // Unused abstract methods of MouseListener interface
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

