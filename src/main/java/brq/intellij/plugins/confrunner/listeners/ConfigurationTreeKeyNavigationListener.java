package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.actions.RunConfigExecutor;
import brq.intellij.plugins.confrunner.dialog.DialogState;
import brq.intellij.plugins.confrunner.ui.BaseConfigurationPanel;
import brq.intellij.plugins.confrunner.ui.JPanelChildrenToggle;
import brq.intellij.plugins.confrunner.ui.JPanelSingleConfiguration;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static brq.intellij.plugins.confrunner.common.utils.FocusUtil.keyEquals;

public class ConfigurationTreeKeyNavigationListener implements KeyListener {
    private final BaseConfigurationPanel configuration;

    public ConfigurationTreeKeyNavigationListener(BaseConfigurationPanel configuration) {
        this.configuration = configuration;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (focusPreviousKeyStroke(e)) {
            DialogState.getInstance().scrollUp(configuration.getLabel());
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
        } else if (focusNextKeyStroke(e)) {
            DialogState.getInstance().scrollDown(configuration.getLabel());
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
        } else if (keyEquals(e, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER)) {
            if (configuration instanceof JPanelChildrenToggle) {
                toggle();
            } else if (keyEquals(e, KeyEvent.VK_ENTER) && configuration instanceof JPanelSingleConfiguration) {
                runConfiguration(e);
            }
        }
    }

    private boolean focusPreviousKeyStroke(KeyEvent e) {
        return keyEquals(e, KeyEvent.VK_UP) || (e.isShiftDown() && keyEquals(e, KeyEvent.VK_TAB));
    }

    private boolean focusNextKeyStroke(KeyEvent e) {
        return keyEquals(e, KeyEvent.VK_DOWN, KeyEvent.VK_TAB);
    }

    private void toggle() {
        ((JPanelChildrenToggle) configuration).toggle();
    }

    private void runConfiguration(KeyEvent e) {
        if (e.isControlDown()) {
            RunConfigExecutor.executeDebug(((JPanelSingleConfiguration) configuration).getExecutable());
        } else {
            RunConfigExecutor.executeRun(((JPanelSingleConfiguration) configuration).getExecutable());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
