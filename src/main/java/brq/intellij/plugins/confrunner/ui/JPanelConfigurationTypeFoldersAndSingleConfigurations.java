package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.domain.ConfigurationFolder;
import brq.intellij.plugins.confrunner.domain.SingleRunConfiguration;
import brq.intellij.plugins.confrunner.common.constants.Constants;

import javax.swing.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JPanelConfigurationTypeFoldersAndSingleConfigurations extends JPanel {
    private List<JPanelConfigurationFolderBox> folderedConfigurationsPanel;
    private List<JPanelSingleConfiguration> nonFolderedConfigurationsPanel;

    public JPanelConfigurationTypeFoldersAndSingleConfigurations() {
        super();
    }

    public void setFolderedConfigurationsPanel(List<JPanelConfigurationFolderBox> folderedConfigurationsPanel) {
        this.folderedConfigurationsPanel = folderedConfigurationsPanel;
        folderedConfigurationsPanel.forEach(this::add);
    }

    public void setNonFolderedConfigurationsPanel(List<JPanelSingleConfiguration> nonFolderedConfigurationsPanel) {
        this.nonFolderedConfigurationsPanel = nonFolderedConfigurationsPanel;
        nonFolderedConfigurationsPanel.forEach(this::add);
    }

    public static JPanelConfigurationTypeFoldersAndSingleConfigurations createPanel(List<ConfigurationFolder> folders, List<SingleRunConfiguration> nonFolderedConfigurations) {
        JPanelConfigurationTypeFoldersAndSingleConfigurations panel = new JPanelConfigurationTypeFoldersAndSingleConfigurations();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        List<JPanelConfigurationFolderBox> folderedConfigurationsPanel = createFolderedConfigurationsToConfigurationTree(folders);
        panel.setFolderedConfigurationsPanel(folderedConfigurationsPanel);

        List<JPanelSingleConfiguration> nonFolderedConfigurationsPanel = createNonFolderedConfigurationsToConfigurationTree(nonFolderedConfigurations);
        panel.setNonFolderedConfigurationsPanel(nonFolderedConfigurationsPanel);

        return panel;
    }

    private static List<JPanelConfigurationFolderBox> createFolderedConfigurationsToConfigurationTree(List<ConfigurationFolder> configurationFolders) {
        return configurationFolders.stream()
                .map(c -> JPanelConfigurationFolderBox.createFolderedConfigurations(c.getFolder(), c.getSingleRunConfigurations()))
                .collect(toList());
    }

    private static List<JPanelSingleConfiguration> createNonFolderedConfigurationsToConfigurationTree(List<SingleRunConfiguration> nonFolderedRunConfigurations) {
        return nonFolderedRunConfigurations.stream()
                //change TYPE_CHILDREN_OFFSET to dynamic - folder icon width
                .map(c -> JPanelSingleConfiguration.createSingleConfiguration(c.getName(), c.getIcon(), c.getExecutable(), Constants.iconWidth))
                .collect(toList());
    }

    public List<JPanelConfigurationFolderBox> getFolderedConfigurationsPanel() {
        return folderedConfigurationsPanel;
    }

    public List<JPanelSingleConfiguration> getNonFolderedConfigurationsPanel() {
        return nonFolderedConfigurationsPanel;
    }
}
