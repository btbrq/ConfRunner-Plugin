package brq.intellij.plugins.confrunner.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intellij.execution.RunnerAndConfigurationSettings;

import javax.swing.*;

public class SingleRunConfiguration {
    private String name;
    @JsonIgnore
    private Icon icon;
    private String typeName;
    @JsonIgnore
    private Icon typeIcon;
    private String folderName;
    @JsonIgnore
    private RunnerAndConfigurationSettings executable;

    public SingleRunConfiguration() {
    }

    public SingleRunConfiguration(String name, Icon icon, String typeName, Icon typeIcon, String folderName, RunnerAndConfigurationSettings executable) {
        this.name = name;
        this.icon = icon;
        this.typeName = typeName;
        this.typeIcon = typeIcon;
        this.folderName = folderName;
        this.executable = executable;
    }

    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getTypeName() {
        return typeName;
    }

    public Icon getTypeIcon() {
        return typeIcon;
    }

    public String getFolderName() {
        return folderName;
    }

    public RunnerAndConfigurationSettings getExecutable() {
        return executable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
