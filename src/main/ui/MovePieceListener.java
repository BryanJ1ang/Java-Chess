package ui;

import java.awt.event.*;

// Listener for pieces
public class MovePieceListener implements MouseListener, MouseMotionListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        e.getComponent().setLocation(e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

        e.getComponent().setLocation(e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }
}
