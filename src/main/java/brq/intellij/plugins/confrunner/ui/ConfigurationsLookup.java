package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.dialog.DialogState;
import brq.intellij.plugins.confrunner.listeners.ConfigurationLookupKeyNavigationListener;
import brq.intellij.plugins.confrunner.listeners.ConfigurationLookupTextFilterListener;
import brq.intellij.plugins.confrunner.common.utils.DimensionsUtil;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ConfigurationsLookup extends JTextField {
    public ConfigurationsLookup() {
        super();
        this.getDocument().addDocumentListener(new ConfigurationLookupTextFilterListener(this));
        this.addKeyListener(new ConfigurationLookupKeyNavigationListener());
        this.addFocusListener(new ConfLookupFocusListener());
        setFocusTraversalKeysEnabled(false);
    }

    public static ConfigurationsLookup createLookup() {
        ConfigurationsLookup lookup = new ConfigurationsLookup();
        DimensionsUtil.setLookupDimensions(lookup);
        return lookup;
    }

    private static class ConfLookupFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            DialogState.getInstance().scrollToTop();
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }

}
