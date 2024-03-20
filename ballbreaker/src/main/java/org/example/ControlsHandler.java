package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlsHandler implements KeyListener {
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    private boolean leftPressed = false, rightPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT)
            leftPressed = true;
        if (keyCode == KeyEvent.VK_RIGHT)
            rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT)
            leftPressed = false;
        if (keyCode == KeyEvent.VK_RIGHT)
            rightPressed = false;
    }
}
