package brq.intellij.plugins.confrunner.domain;

import java.util.List;

public class ConfigurationFolder {
    private String folder;
    private List<SingleRunConfiguration> singleRunConfigurations;

    public ConfigurationFolder() {
    }

    public ConfigurationFolder(String name, List<SingleRunConfiguration> singleRunConfigurations) {
        this.folder = name;
        this.singleRunConfigurations = singleRunConfigurations;
    }

    public String getFolder() {
        return folder;
    }

    public List<SingleRunConfiguration> getSingleRunConfigurations() {
        return singleRunConfigurations;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setSingleRunConfigurations(List<SingleRunConfiguration> singleRunConfigurations) {
        this.singleRunConfigurations = singleRunConfigurations;
    }
}
