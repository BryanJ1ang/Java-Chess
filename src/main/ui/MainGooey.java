package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// main menu component of gui
public class MainGooey implements ActionListener {

    JFrame jframe;

    public static void main(String[] args) {
        new MainGooey();
    }

    private JPanel mainMenuPanel() {
        JFrame jframe = new JFrame();
        JPanel panel = new JPanel();

        JButton newGame = new JButton("New Game");
        JButton savedGame = new JButton("Saved Games");
        JButton customGame = new JButton("Custom Game");
        JButton quit = new JButton("Quit");

        newGame.addActionListener(this);
        savedGame.addActionListener(this);
        customGame.addActionListener(this);
        quit.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(newGame);
        panel.add(savedGame);
        panel.add(customGame);
        panel.add(quit);
        return panel;
    }

    // EFFECTS: Constructor for MainGooey
    public MainGooey() {
        jframe = new JFrame();

        jframe.add(mainMenuPanel(), BorderLayout.CENTER);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("Chess");
        jframe.pack();
        jframe.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game")) {
            jframe.setVisible(false);
            Game g = new Game("default", null, true);
            new GameGooey(g);
        }
        if (e.getActionCommand().equals("Saved Games")) {

        }
        if (e.getActionCommand().equals("Custom Game")) {

        }
        if (e.getActionCommand().equals("Quit")) {
            System.exit(1);
        }

    }
}
