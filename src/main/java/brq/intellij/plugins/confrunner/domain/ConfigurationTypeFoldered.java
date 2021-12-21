package brq.intellij.plugins.confrunner.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.swing.*;
import java.util.List;

public class ConfigurationTypeFoldered {
    private String type;
    @JsonIgnore
    private Icon typeIcon;
    private List<ConfigurationFolder> configurationFolders;
    private List<SingleRunConfiguration> nonFolderedRunConfigurations;

    public ConfigurationTypeFoldered() {
    }

    public ConfigurationTypeFoldered(String type, Icon typeIcon, List<ConfigurationFolder> configurationFolders, List<SingleRunConfiguration> nonFolderedRunConfigurations) {
        this.type = type;
        this.typeIcon = typeIcon;
        this.configurationFolders = configurationFolders;
        this.nonFolderedRunConfigurations = nonFolderedRunConfigurations;
    }

    public String getType() {
        return type;
    }

    public Icon getTypeIcon() {
        return typeIcon;
    }

    public List<ConfigurationFolder> getConfigurationFolders() {
        return configurationFolders;
    }

    public List<SingleRunConfiguration> getNonFolderedRunConfigurations() {
        return nonFolderedRunConfigurations;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setConfigurationFolders(List<ConfigurationFolder> configurationFolders) {
        this.configurationFolders = configurationFolders;
    }

    public void setNonFolderedRunConfigurations(List<SingleRunConfiguration> nonFolderedRunConfigurations) {
        this.nonFolderedRunConfigurations = nonFolderedRunConfigurations;
    }
}
