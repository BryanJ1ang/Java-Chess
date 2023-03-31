package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Saves;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

// Save menu of GUI
public class SavesGooey implements ActionListener {
    String file;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    Saves saves = new Saves();
    int status = -1;

    // EFFECTS: Constructor for SavesGooey
    public SavesGooey() {
        panel.setLayout(new GridLayout(0,1));
        frame.setBackground(Color.black);
        displaySaves();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: Displays list of saved files
    private void displaySaves() {
        panel.removeAll();
        for (Object o : saves.listOfSaves()) {
            String s = (String) o;
            if (s != null && saves.doesSaveExist(s)) {
                JButton button = new JButton(s);
                button.addActionListener(this);
                panel.add(button);
            }
        }
        JButton button = new JButton("Main Menu");
        button.addActionListener(this);
        button.setBackground(Color.cyan);

        panel.add(button);
        panel.revalidate();
        panel.repaint();
    }

    // EFFECTS: Displays load/delete buttons
    private void displayLoadOrSave() {
        panel.removeAll();
        JButton button = new JButton("Load");
        button.addActionListener(this);
        panel.add(button);
        button = new JButton("Delete");
        button.addActionListener(this);
        panel.add(button);

        panel.revalidate();
        panel.repaint();
    }

    // EFFECTS: Deletes selected save
    private void loadSave() {
        JsonReader reader = new JsonReader("./data/" + file + ".json");
        try {
            new GameGooey(reader.read());
            frame.dispose();
        } catch (IOException e) {
            System.exit(5);
        }
    }

    // EFFECTS: Deletes selected save
    private void deleteSave() {
        try {
            saves.removeFile(file);
        } catch (IOException e) {
            System.exit(5);
        }
        displaySaves();
    }

    // EFFECTS: Handles selection and whether to load/delete save
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Load")) {
            loadSave();
        }
        if (e.getActionCommand().equals("Main Menu")) {
            frame.dispose();
        }
        if (e.getActionCommand().equals("Delete")) {
            deleteSave();
        } else {
            file = e.getActionCommand();
            displayLoadOrSave();
        }
    }
}
