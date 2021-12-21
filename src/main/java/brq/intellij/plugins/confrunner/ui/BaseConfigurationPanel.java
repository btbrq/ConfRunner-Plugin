package brq.intellij.plugins.confrunner.ui;

import javax.swing.*;

public class BaseConfigurationPanel extends JPanel {
    private String labelText;

    public BaseConfigurationPanel(String labelText) {
        this.labelText = labelText;
    }

    public BaseConfigurationPanel() {}

    public String getLabelText() {
        return labelText;
    }
}
