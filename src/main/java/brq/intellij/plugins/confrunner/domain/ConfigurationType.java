package brq.intellij.plugins.confrunner.domain;

import javax.swing.*;
import java.util.List;

public class ConfigurationType {
    private final String type;
    private final Icon typeIcon;
    private final List<SingleRunConfiguration> singleRunConfigurations;

    public ConfigurationType(String type, Icon typeIcon, List<SingleRunConfiguration> singleRunConfigurations) {
        this.type = type;
        this.typeIcon = typeIcon;
        this.singleRunConfigurations = singleRunConfigurations;
    }

    public String getType() {
        return type;
    }

    public Icon getTypeIcon() {
        return typeIcon;
    }

    public List<SingleRunConfiguration> getSingleRunConfigurations() {
        return singleRunConfigurations;
    }
}
