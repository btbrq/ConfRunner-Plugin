package brq.intellij.plugins.confrunner.ui;

import javax.swing.*;

public class BaseConfigurationPanel extends JPanel {
    private JPanelConfigurationLabel label;
    private String labelText;

    public BaseConfigurationPanel(JPanelConfigurationLabel label) {
        this.label = label;
        this.labelText = label.getLabelText();
    }

    public BaseConfigurationPanel() {}

    public JPanelConfigurationLabel getLabel() {
        return label;
    }

    public String getLabelText() {
        return labelText;
    }
}
