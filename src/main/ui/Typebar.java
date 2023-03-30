package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Typebar implements ActionListener {
    JTextField textField = new JTextField(20);
    String text;

    public String getText() {
        return text;
    }

    public JTextField getTextField() {
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
