package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.domain.SingleRunConfiguration;
import brq.intellij.plugins.confrunner.common.constants.Constants;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class JPanelConfigurationFolderConfigurations extends JPanel {
    private List<JPanelSingleConfiguration> configurations;

    public JPanelConfigurationFolderConfigurations() {
        super();
    }

    public void setConfigurations(List<JPanelSingleConfiguration> configurations) {
        this.configurations = configurations;
        configurations.forEach(this::add);
    }

    public static JPanelConfigurationFolderConfigurations createPanel(List<SingleRunConfiguration> singleRunConfigurations) {
        JPanelConfigurationFolderConfigurations configurationsBox = new JPanelConfigurationFolderConfigurations();
        configurationsBox.setLayout(new BoxLayout(configurationsBox, BoxLayout.PAGE_AXIS));
        List<JPanelSingleConfiguration> configurations = createConfigurations(singleRunConfigurations);
        configurationsBox.setConfigurations(configurations);

        return configurationsBox;
    }

    @NotNull
    private static List<JPanelSingleConfiguration> createConfigurations(List<SingleRunConfiguration> singleRunConfigurations) {
        return singleRunConfigurations.stream()
                .map(c -> JPanelSingleConfiguration.createSingleConfiguration(c.getName(), c.getIcon(), c.getExecutable(), Constants.folderIconWidth))
                .collect(toList());
    }

    public List<JPanelSingleConfiguration> getConfigurations() {
        return configurations;
    }
}
