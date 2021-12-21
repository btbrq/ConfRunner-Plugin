package brq.intellij.plugins.confrunner.domain;

import java.util.Collections;
import java.util.List;

public class ConfigurationTree {
    private List<ConfigurationTypeFoldered> configurations;

    public ConfigurationTree() {
    }

    public ConfigurationTree(List<ConfigurationTypeFoldered> configurations) {
        this.configurations = configurations;
    }

    public List<ConfigurationTypeFoldered> getConfigurations() {
        return Collections.unmodifiableList(configurations);
    }

    public void setConfigurations(List<ConfigurationTypeFoldered> configurations) {
        this.configurations = configurations;
    }
}
