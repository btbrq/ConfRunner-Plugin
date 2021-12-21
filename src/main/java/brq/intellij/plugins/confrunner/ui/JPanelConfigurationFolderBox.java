package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.common.constants.Constants;
import brq.intellij.plugins.confrunner.domain.SingleRunConfiguration;
import brq.intellij.plugins.confrunner.listeners.ConfigurationTreeKeyNavigationListener;
import brq.intellij.plugins.confrunner.listeners.MouseClickListener;
import brq.intellij.plugins.confrunner.listeners.FocusListener;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.util.List;

import static com.intellij.icons.AllIcons.Nodes.Folder;

public class JPanelConfigurationFolderBox extends BaseConfigurationPanel implements JPanelChildrenToggle {
    private JPanelConfigurationFolderConfigurations configurations;
    private JPanelConfigurationLabel label;

    private JPanelConfigurationFolderBox(JPanelConfigurationLabel label) {
        super(label.getLabelText());
        this.label = label;
        label.addFocusListener(new FocusListener(label));
        label.addMouseListener(new MouseClickListener(this, label));
        label.addKeyListener(new ConfigurationTreeKeyNavigationListener(this));
        label.setBorder(JBUI.Borders.emptyLeft(Constants.iconWidth));
        add(label);
    }

    private void setConfigurations(JPanelConfigurationFolderConfigurations configurations) {
        this.configurations = configurations;
        configurations.setVisible(true);
        add(configurations);
    }

    @Override
    public void toggle() {
        if (configurations != null) {
            configurations.setVisible(!configurations.isVisible());
            if (configurations.isVisible()) {
                label.toggleExpanded();
            } else {
                label.toggleCollapsed();
            }
        }
    }

    public static JPanelConfigurationFolderBox createFolderedConfigurations(String folder, List<SingleRunConfiguration> singleRunConfigurations) {
        JPanelConfigurationFolderBox box = new JPanelConfigurationFolderBox(JPanelConfigurationLabel.createTypeFolderLabel(folder, Folder));
        box.setLayout((new BoxLayout(box, BoxLayout.PAGE_AXIS)));
        box.setOpaque(true);

        JPanelConfigurationFolderConfigurations configurations = JPanelConfigurationFolderConfigurations.createPanel(singleRunConfigurations);
        box.setConfigurations(configurations);
        return box;
    }

    public JPanelConfigurationFolderConfigurations getConfigurations() {
        return configurations;
    }
}
