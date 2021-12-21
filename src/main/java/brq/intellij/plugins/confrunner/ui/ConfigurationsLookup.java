package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.listeners.ConfigurationLookupKeyNavigationListener;
import brq.intellij.plugins.confrunner.listeners.ConfigurationLookupTextFilterListener;
import brq.intellij.plugins.confrunner.common.utils.DimensionsUtil;

import javax.swing.*;

public class ConfigurationsLookup extends JTextField {
    public ConfigurationsLookup() {
        super();
        this.getDocument().addDocumentListener(new ConfigurationLookupTextFilterListener(this));
        this.addKeyListener(new ConfigurationLookupKeyNavigationListener());
    }

    public static ConfigurationsLookup createLookup() {
        ConfigurationsLookup lookup = new ConfigurationsLookup();
        DimensionsUtil.setLookupDimensions(lookup);
        return lookup;
    }
}
