package brq.intellij.plugins.confrunner.settings;

import javax.swing.*;

import static brq.intellij.plugins.confrunner.common.constants.Constants.APP_NAME;

public class Configurable implements com.intellij.openapi.options.Configurable {

    private JPanelSettings settingsPanel;

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsPanel;
    }

    @Override
    public JComponent createComponent() {
        settingsPanel = JPanelSettings.createAppSettingsPanel();
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        return isModified(Settings.getInstance());
    }

    private boolean isModified(Settings settings) {
        return settingsPanel.isExcludeTemporaryConfigurationsSelected() != settings.isExcludeTemporaryConfigurations()
                || settingsPanel.isExcludeStoredInIdeaRunConfigurations() != settings.isExcludeStoredInIdeaRunConfigurations()
                || settingsPanel.isExcludeStoredInArbitraryFileInProject() != settings.isExcludeStoredInArbitraryFileInProject()
                || settingsPanel.isExcludeLocalNotSharedThroughVCS() != settings.isExcludeLocalNotSharedThroughVCS();
    }

    @Override
    public void apply() {
        Settings settings = Settings.getInstance();
        settings.setExcludeTemporaryConfigurations(settingsPanel.isExcludeTemporaryConfigurationsSelected());
        settings.setExcludeStoredInIdeaRunConfigurations(settingsPanel.isExcludeStoredInIdeaRunConfigurations());
        settings.setExcludeStoredInArbitraryFileInProject(settingsPanel.isExcludeStoredInArbitraryFileInProject());
        settings.setExcludeLocalNotSharedThroughVCS(settingsPanel.isExcludeLocalNotSharedThroughVCS());
    }

    @Override
    public void reset() {
        Settings settings = Settings.getInstance();
        settingsPanel.setExcludeTemporaryConfigurations(settings.isExcludeTemporaryConfigurations());
        settingsPanel.setExcludeStoredInIdeaRunConfigurations(settings.isExcludeStoredInIdeaRunConfigurations());
        settingsPanel.setExcludeStoredInArbitraryFileInProject(settings.isExcludeStoredInArbitraryFileInProject());
        settingsPanel.setExcludeLocalNotSharedThroughVCS(settings.isExcludeLocalNotSharedThroughVCS());
    }

    @Override
    public void disposeUIResources() {
        settingsPanel = null;
    }

    @Override
    public String getDisplayName() {
        return APP_NAME;
    }

}
