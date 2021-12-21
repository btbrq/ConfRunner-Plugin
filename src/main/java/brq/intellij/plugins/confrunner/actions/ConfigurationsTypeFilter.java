package brq.intellij.plugins.confrunner.actions;

import brq.intellij.plugins.confrunner.settings.Settings;
import com.intellij.execution.RunnerAndConfigurationSettings;

import java.util.function.Predicate;

public class ConfigurationsTypeFilter {
    private static final Predicate<RunnerAndConfigurationSettings> excludeTemporary = c -> !c.isTemporary();
    private static final Predicate<RunnerAndConfigurationSettings> excludeStoredInIdeaRunConfigurations = c -> !c.isStoredInDotIdeaFolder();
    private static final Predicate<RunnerAndConfigurationSettings> excludeStoredInArbitraryFileInProject = c -> !c.isStoredInArbitraryFileInProject();
    private static final Predicate<RunnerAndConfigurationSettings> excludeLocalNotSharedThroughVCS = c -> !c.isStoredInLocalWorkspace();

    public static Predicate<RunnerAndConfigurationSettings> filterPredicate(Settings settings) {
        Predicate<RunnerAndConfigurationSettings> basePredicate = c -> true;
        if (settings.isExcludeTemporaryConfigurations()) {
            basePredicate = basePredicate.and(excludeTemporary);
        }
        if (settings.isExcludeStoredInIdeaRunConfigurations()) {
            basePredicate = basePredicate.and(excludeStoredInIdeaRunConfigurations);
        }
        if (settings.isExcludeStoredInArbitraryFileInProject()) {
            basePredicate = basePredicate.and(excludeStoredInArbitraryFileInProject);
        }
        if (settings.isExcludeLocalNotSharedThroughVCS()) {
            basePredicate = basePredicate.and(excludeLocalNotSharedThroughVCS);
        }
        return basePredicate;
    }
}
