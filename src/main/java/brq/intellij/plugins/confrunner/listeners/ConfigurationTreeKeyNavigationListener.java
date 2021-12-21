package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.actions.RunConfigExecutor;
import brq.intellij.plugins.confrunner.dialog.DialogState;
import brq.intellij.plugins.confrunner.ui.BaseConfigurationPanel;
import brq.intellij.plugins.confrunner.ui.JPanelChildrenToggle;
import brq.intellij.plugins.confrunner.ui.JPanelSingleConfiguration;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.IntStream;

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
        if (keyEquals(e, KeyEvent.VK_DOWN)) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
        } else if (keyEquals(e, KeyEvent.VK_UP)) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
        } else if (keyEquals(e, KeyEvent.VK_F) && e.isControlDown()) {
            DialogState.getInstance().focusConfigurationsLookup();
        } else if (keyEquals(e, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER)) {
            if (configuration instanceof JPanelChildrenToggle) {
                toggle();
            } else if (keyEquals(e, KeyEvent.VK_ENTER) && configuration instanceof JPanelSingleConfiguration) {
                runConfiguration(e);
            }
        }
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

    private boolean keyEquals(KeyEvent e, int... vkDown) {
        return IntStream.of(vkDown).anyMatch(k -> e.getKeyCode() == k);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
