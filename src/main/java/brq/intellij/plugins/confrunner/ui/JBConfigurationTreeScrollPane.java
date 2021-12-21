package brq.intellij.plugins.confrunner.ui;

import com.intellij.ui.components.JBScrollPane;

public class JBConfigurationTreeScrollPane extends JBScrollPane {
    public JBConfigurationTreeScrollPane(JPanelConfigurationTree configurationTree) {
        super(configurationTree, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
    }

    public static JBConfigurationTreeScrollPane createConfigurationTreeScrollPane(JPanelConfigurationTree configurationTree) {
        return new JBConfigurationTreeScrollPane(configurationTree);
    }

}
