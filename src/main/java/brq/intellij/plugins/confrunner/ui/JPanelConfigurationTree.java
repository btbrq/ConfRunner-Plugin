package brq.intellij.plugins.confrunner.ui;

import javax.swing.*;
import java.util.List;

public class JPanelConfigurationTree extends BaseConfigurationPanel {
    private List<JPanelConfigurationTypeBox> configurationTypeBoxes;

    private JPanelConfigurationTree() {
        super();
    }

    public void setConfigurationTypeBoxes(List<JPanelConfigurationTypeBox> configurationTypeBoxes) {
        this.configurationTypeBoxes = configurationTypeBoxes;
        configurationTypeBoxes.forEach(this::add);
    }

    public static JPanelConfigurationTree createEmptyConfigurationTree() {
        JPanelConfigurationTree tree = new JPanelConfigurationTree();
        tree.setLayout((new BoxLayout(tree, BoxLayout.PAGE_AXIS)));
        return tree;
    }

    public List<JPanelConfigurationTypeBox> getConfigurationTypeBoxes() {
        return configurationTypeBoxes;
    }
}
