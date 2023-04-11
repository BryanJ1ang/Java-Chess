package ui;

import model.Event;
import model.EventLog;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// main menu component of gui
public class MainGooey implements ActionListener, WindowListener {

    JFrame jframe;

    public static void main(String[] args) {
        new MainGooey();
    }

    // EFFECTS: setups main menu panel of game
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
        jframe.addWindowListener(this);
        jframe.setVisible(true);
    }



    // EFFECTS: makes frame visible
    public void makeVisible() {
        jframe.setVisible(true);
    }

    // EFFECTS: Processes handling of buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game")) {
            jframe.setVisible(false);
            Game g = new Game("default", null, true);
            new GameGooey(g);
            jframe.setVisible(true);
        }
        if (e.getActionCommand().equals("Saved Games")) {
            new SavesGooey();
        }
        if (e.getActionCommand().equals("Custom Game")) {
            jframe.dispose();
        }
        if (e.getActionCommand().equals("Quit")) {
            jframe.dispose();
        }

    }

    // EFFECTS: Displays event log
    public void displayLog() {
        EventLog log = EventLog.getInstance();
        for (Event event : log) {
            System.out.println(event.getDescription());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        displayLog();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        displayLog();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
