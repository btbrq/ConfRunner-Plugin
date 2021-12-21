package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.actions.ConfigurationsLookupFilter;
import brq.intellij.plugins.confrunner.dialog.DialogState;
import brq.intellij.plugins.confrunner.ui.ConfigurationsLookup;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTypeBox;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class ConfigurationLookupTextFilterListener implements DocumentListener {
    private final ConfigurationsLookup lookup;

    public ConfigurationLookupTextFilterListener(ConfigurationsLookup lookup) {
        this.lookup = lookup;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String text = lookup.getText();
        List<JPanelConfigurationTypeBox> types = DialogState.getInstance().getConfigurationTypeBoxes();
        ConfigurationsLookupFilter.filterConfigurations(types, text);
    }
}
