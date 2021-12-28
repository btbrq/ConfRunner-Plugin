package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.dialog.DialogState;
import brq.intellij.plugins.confrunner.ui.BaseConfigurationPanel;
import brq.intellij.plugins.confrunner.ui.JPanelChildrenToggle;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationLabel;
import brq.intellij.plugins.confrunner.ui.JPanelSingleConfiguration;
import com.intellij.openapi.actionSystem.CommonShortcuts;
import com.intellij.openapi.actionSystem.KeyboardShortcut;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.util.stream.Collectors.toList;

public class ListenerRegisterUtils {

    public static void registerFolderTypeListeners(JPanelChildrenToggle configuration, JPanelConfigurationLabel label) {
        label.addFocusListener(new FocusListener(label));
        label.addMouseListener(new MouseClickListener(configuration, label));
        label.addKeyListener(new ConfigurationTreeKeyNavigationListener((BaseConfigurationPanel) configuration));
        registerGoToLookupComponentAction(label);
    }

    public static void registerSingleConfigurationListeners(JPanelSingleConfiguration configuration, JPanelConfigurationLabel label) {
        label.addFocusListener(new FocusListener(label));
        label.addMouseListener(new SingleRunConfigurationMouseClickListener(label, configuration));
        label.addKeyListener(new ConfigurationTreeKeyNavigationListener(configuration));
        registerGoToLookupComponentAction(label);
    }

    private static void registerGoToLookupComponentAction(JPanelConfigurationLabel label) {
        getFindShortcuts().forEach(s -> label.registerKeyboardAction(k -> DialogState.getInstance().focusConfigurationsLookup(), s, JComponent.WHEN_FOCUSED));
    }

    public static List<KeyStroke> getFindShortcuts() {
        List<KeyStroke> shortcuts = Arrays.stream(CommonShortcuts.getFind().getShortcuts()).map(k -> {
            if (k instanceof KeyboardShortcut) {
                KeyboardShortcut s = (KeyboardShortcut) k;
                return s.getFirstKeyStroke();
            }
            return null;
        }).filter(Objects::nonNull).collect(toList());

        if (shortcuts.isEmpty()) {
            shortcuts.add(KeyStroke.getKeyStroke(KeyEvent.VK_F, CTRL_DOWN_MASK));
        }

        return shortcuts;
    }
}
