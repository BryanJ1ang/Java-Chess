package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Saves;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

// Saves menu of GUI
public class SavesGooey implements ActionListener {
    String file;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    Saves saves = new Saves();
    int status = -1;

    public SavesGooey() {
        displaySaves();
        for (Object o: saves.listOfSaves()) {
            String s = (String) o;
            if (s != null && saves.doesSaveExist(s)) {
                JButton button = new JButton(s);
                button.addActionListener(this);
                panel.add(button);
            }
        }

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.pack();
        frame.setVisible(true);
    }

    private void displaySaves() {
        panel.removeAll();
        for (Object o: saves.listOfSaves()) {
            String s = (String) o;
            if (s != null && saves.doesSaveExist(s)) {
                JButton button = new JButton(s);
                button.addActionListener(this);
                panel.add(button);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

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

    private void loadSave() {
        JsonReader reader = new JsonReader("./data/" + file + ".json");
        try {
           new GameGooey(reader.read());
            frame.dispose();
        } catch (IOException e) {
            System.exit(5);
        }
    }

    private void deleteSave() {
        try {
            saves.removeFile(file);
        } catch (IOException e) {
            System.exit(5);
        }
        displaySaves();
    }

    private void loadordelete() {
        displayLoadOrSave();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Load")) {
            loadSave();
        }
        if (e.getActionCommand().equals("Delete")) {
            deleteSave();
        } else {
            file = e.getActionCommand();
            displayLoadOrSave();
        }
    }
}
