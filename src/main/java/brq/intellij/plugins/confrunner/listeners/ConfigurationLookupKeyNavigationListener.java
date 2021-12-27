package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.dialog.DialogState;

import java.awt.*;
import java.awt.event.KeyEvent;

import static brq.intellij.plugins.confrunner.common.utils.FocusUtil.keyEquals;

public class ConfigurationLookupKeyNavigationListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (focusPreviousKeyStroke(e)) {
            DialogState.getInstance().scrollToBottom();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
        } else if (focusNextKeyStroke(e)) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
        }
    }

    private boolean focusPreviousKeyStroke(KeyEvent e) {
        return keyEquals(e, KeyEvent.VK_UP) || (e.isShiftDown() && keyEquals(e, KeyEvent.VK_TAB));
    }

    private boolean focusNextKeyStroke(KeyEvent e) {
        return keyEquals(e, KeyEvent.VK_DOWN, KeyEvent.VK_TAB);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
