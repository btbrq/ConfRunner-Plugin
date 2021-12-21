package brq.intellij.plugins.confrunner.settings;

import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.JBCheckBox;

import javax.swing.*;

public class JPanelSettings extends JPanel {

    private final JBCheckBox excludeTemporaryConfigurations;
    private final JBCheckBox excludeStoredInIdeaRunConfigurations;
    private final JBCheckBox excludeStoredInArbitraryFileInProject;
    private final JBCheckBox excludeLocalNotSharedThroughVCS;

    private JPanelSettings() {
        excludeTemporaryConfigurations = new JBCheckBox("Exclude temporary configurations.");
        excludeStoredInIdeaRunConfigurations = new JBCheckBox("Exclude configurations stored in .idea/runConfigurations folder.");
        excludeStoredInArbitraryFileInProject = new JBCheckBox("Exclude configurations stored in arbitrary file in project.");
        excludeLocalNotSharedThroughVCS = new JBCheckBox("Exclude local configurations, not shared through VCS.");

        add(excludeTemporaryConfigurations);
        add(excludeStoredInIdeaRunConfigurations);
        add(excludeStoredInArbitraryFileInProject);
        add(excludeLocalNotSharedThroughVCS);
    }

    public static JPanelSettings createAppSettingsPanel() {
        JPanelSettings settingsPanel = new JPanelSettings();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));
        settingsPanel.setBorder(IdeBorderFactory.createTitledBorder("Filter Configurations"));
        return settingsPanel;
    }

    public boolean isExcludeTemporaryConfigurationsSelected() {
        return excludeTemporaryConfigurations.isSelected();
    }

    public void setExcludeTemporaryConfigurations(boolean selected) {
        excludeTemporaryConfigurations.setSelected(selected);
    }

    public boolean isExcludeStoredInIdeaRunConfigurations() {
        return excludeStoredInIdeaRunConfigurations.isSelected();
    }

    public void setExcludeStoredInIdeaRunConfigurations(boolean selected) {
        excludeStoredInIdeaRunConfigurations.setSelected(selected);
    }

    public boolean isExcludeStoredInArbitraryFileInProject() {
        return excludeStoredInArbitraryFileInProject.isSelected();
    }

    public void setExcludeStoredInArbitraryFileInProject(boolean selected) {
        excludeStoredInArbitraryFileInProject.setSelected(selected);
    }

    public boolean isExcludeLocalNotSharedThroughVCS() {
        return excludeLocalNotSharedThroughVCS.isSelected();
    }

    public void setExcludeLocalNotSharedThroughVCS(boolean selected) {
        excludeLocalNotSharedThroughVCS.setSelected(selected);
    }

}
