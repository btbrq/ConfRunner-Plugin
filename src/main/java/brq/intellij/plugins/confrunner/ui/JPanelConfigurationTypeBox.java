package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.domain.ConfigurationTypeFoldered;

import javax.swing.*;

import static brq.intellij.plugins.confrunner.listeners.ListenerRegisterUtils.registerFolderTypeListeners;

public class JPanelConfigurationTypeBox extends BaseConfigurationPanel implements JPanelChildrenToggle {
    private JPanelConfigurationTypeFoldersAndSingleConfigurations foldersAndSingleConfigurations;

    private JPanelConfigurationTypeBox(JPanelConfigurationLabel label) {
        super(label);
        registerFolderTypeListeners(this, label);
        add(label);
    }

    public void setFoldersAndSingleConfigurations(JPanelConfigurationTypeFoldersAndSingleConfigurations foldersAndSingleConfigurations) {
        this.foldersAndSingleConfigurations = foldersAndSingleConfigurations;
        foldersAndSingleConfigurations.setVisible(true);
        add(foldersAndSingleConfigurations);
    }

    @Override
    public void toggle() {
        if (foldersAndSingleConfigurations != null) {
            foldersAndSingleConfigurations.setVisible(!foldersAndSingleConfigurations.isVisible());
            if (foldersAndSingleConfigurations.isVisible()) {
                getLabel().toggleExpanded();
            } else {
                getLabel().toggleCollapsed();
            }
        }
    }

    public static JPanelConfigurationTypeBox createTypeBox(ConfigurationTypeFoldered c) {
        JPanelConfigurationTypeBox box = new JPanelConfigurationTypeBox(JPanelConfigurationLabel.createTypeFolderLabel(c.getType(), c.getTypeIcon()));
        box.setLayout((new BoxLayout(box, BoxLayout.PAGE_AXIS)));
        box.setOpaque(true);

        JPanelConfigurationTypeFoldersAndSingleConfigurations foldersAndSingleConfigurations = JPanelConfigurationTypeFoldersAndSingleConfigurations.createPanel(
                c.getConfigurationFolders(), c.getNonFolderedRunConfigurations()
        );
        box.setFoldersAndSingleConfigurations(foldersAndSingleConfigurations);
        return box;
    }

    public JPanelConfigurationTypeFoldersAndSingleConfigurations getFoldersAndSingleConfigurations() {
        return foldersAndSingleConfigurations;
    }
}
