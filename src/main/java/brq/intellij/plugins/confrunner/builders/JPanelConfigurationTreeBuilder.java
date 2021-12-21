package brq.intellij.plugins.confrunner.builders;

import brq.intellij.plugins.confrunner.domain.ConfigurationTree;
import brq.intellij.plugins.confrunner.settings.Settings;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTree;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTypeBox;
import com.intellij.execution.impl.RunManagerImpl;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class JPanelConfigurationTreeBuilder {
    public static JPanelConfigurationTree getJPanelConfigurationTree(RunManagerImpl instanceImpl, Settings settings) {
        ConfigurationTree configurationTree = ConfigurationTreeBuilder.buildConfigurationTree(instanceImpl.getAllSettings(), settings);
        return transformConfigurationTreeIntoJPanelTree(configurationTree);
    }

    public static JPanelConfigurationTree transformConfigurationTreeIntoJPanelTree(ConfigurationTree configurationTree) {
        JPanelConfigurationTree tree = JPanelConfigurationTree.createEmptyConfigurationTree();
        List<JPanelConfigurationTypeBox> confTypeBoxes = configurationTree.getConfigurations()
                .stream()
                .map(JPanelConfigurationTypeBox::createTypeBox)
                .collect(toList());
        tree.setConfigurationTypeBoxes(confTypeBoxes);
        return tree;
    }
}
