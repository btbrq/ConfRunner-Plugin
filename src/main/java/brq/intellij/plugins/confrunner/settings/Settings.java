package brq.intellij.plugins.confrunner.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@State(name = "brq.intellij.plugins.confrunner.settings.Settings", storages = @Storage("confrunner-settings.xml"))
public class Settings implements PersistentStateComponent<Settings> {

    private boolean excludeTemporaryConfigurations = false;
    private boolean excludeStoredInIdeaRunConfigurations = false;
    private boolean excludeStoredInArbitraryFileInProject = false;
    private boolean excludeLocalNotSharedThroughVCS = false;

    public static Settings getInstance() {
        return ApplicationManager.getApplication().getService(Settings.class);
    }

    @Override
    public Settings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Settings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isExcludeTemporaryConfigurations() {
        return excludeTemporaryConfigurations;
    }

    public void setExcludeTemporaryConfigurations(boolean excludeTemporaryConfigurations) {
        this.excludeTemporaryConfigurations = excludeTemporaryConfigurations;
    }

    public boolean isExcludeStoredInIdeaRunConfigurations() {
        return excludeStoredInIdeaRunConfigurations;
    }

    public void setExcludeStoredInIdeaRunConfigurations(boolean excludeStoredInIdeaRunConfigurations) {
        this.excludeStoredInIdeaRunConfigurations = excludeStoredInIdeaRunConfigurations;
    }

    public boolean isExcludeStoredInArbitraryFileInProject() {
        return excludeStoredInArbitraryFileInProject;
    }

    public void setExcludeStoredInArbitraryFileInProject(boolean excludeStoredInArbitraryFileInProject) {
        this.excludeStoredInArbitraryFileInProject = excludeStoredInArbitraryFileInProject;
    }

    public boolean isExcludeLocalNotSharedThroughVCS() {
        return excludeLocalNotSharedThroughVCS;
    }

    public void setExcludeLocalNotSharedThroughVCS(boolean excludeLocalNotSharedThroughVCS) {
        this.excludeLocalNotSharedThroughVCS = excludeLocalNotSharedThroughVCS;
    }
}
