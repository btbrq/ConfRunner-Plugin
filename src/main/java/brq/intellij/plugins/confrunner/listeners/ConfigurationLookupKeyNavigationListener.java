package brq.intellij.plugins.confrunner.listeners;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ConfigurationLookupKeyNavigationListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
