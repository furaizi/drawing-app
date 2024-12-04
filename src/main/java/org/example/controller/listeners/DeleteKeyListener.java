package org.example.controller.listeners;

import org.example.controller.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DeleteKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE)
            Controller.getInstance().handleDeleteKey();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
