package brq.intellij.plugins.confrunner.builders;

import brq.intellij.plugins.confrunner.actions.ConfigurationsTypeFilter;
import brq.intellij.plugins.confrunner.domain.*;
import brq.intellij.plugins.confrunner.settings.Settings;
import com.intellij.execution.RunnerAndConfigurationSettings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class ConfigurationTreeBuilder {
    public static ConfigurationTree buildConfigurationTree(List<RunnerAndConfigurationSettings> configurations, Settings settings) {
        List<SingleRunConfiguration> runConfigurations = buildSingleRunConfigurationsSet(configurations, settings);
        List<ConfigurationType> configurationTypeList = transformRunConfigurationsIntoConfigurationTypes(runConfigurations);
        List<ConfigurationTypeFoldered> configurationTypeFolders = transformRunConfigurationTypesIntoTypesFolderConfigurations(configurationTypeList);
        return new ConfigurationTree(configurationTypeFolders);
    }

    private static List<SingleRunConfiguration> buildSingleRunConfigurationsSet(List<RunnerAndConfigurationSettings> configurations, Settings settings) {
        return configurations.stream()
                .filter(ConfigurationsTypeFilter.filterPredicate(settings))
                .map(c -> {
                    String name = c.getName();
                    Icon icon = c.getType().getIcon();
                    Icon typeIcon = c.getType().getIcon();
                    String typeName = c.getType().getDisplayName();
                    String folderName = c.getFolderName();
                    return new SingleRunConfiguration(name, icon, typeName, typeIcon, folderName, c);
                })
                .collect(toList());
    }

    private static List<ConfigurationType> transformRunConfigurationsIntoConfigurationTypes(List<SingleRunConfiguration> runConfigurations) {
        Map<String, List<SingleRunConfiguration>> configurationTypes = transformRunConfigurationsIntoConfigurationTypesMap(runConfigurations);
        List<ConfigurationType> configurationTypeList = new ArrayList<>();
        for (String type : configurationTypes.keySet()) {
            List<SingleRunConfiguration> singleRunConfigurations = configurationTypes.get(type);
            Icon typeIcon = getTypeIcon(singleRunConfigurations);
            configurationTypeList.add(new ConfigurationType(type, typeIcon, singleRunConfigurations));
        }
        return configurationTypeList;
    }

    private static Map<String, List<SingleRunConfiguration>> transformRunConfigurationsIntoConfigurationTypesMap(List<SingleRunConfiguration> runConfigurations) {
        return runConfigurations.stream().collect(groupingBy(SingleRunConfiguration::getTypeName));
    }

    private static List<ConfigurationTypeFoldered> transformRunConfigurationTypesIntoTypesFolderConfigurations(List<ConfigurationType> configurationTypeList) {
        return configurationTypeList.stream().map(c -> {
            List<ConfigurationFolder> configurationFolders = getConfigurationFolders(c);
            List<SingleRunConfiguration> nonFolderedConfigurations = getNonFolderedConfigurations(c);
            return new ConfigurationTypeFoldered(c.getType(), c.getTypeIcon(), configurationFolders, nonFolderedConfigurations);
        }).collect(toList());
    }

    private static List<ConfigurationFolder> getConfigurationFolders(ConfigurationType configurationType) {
        Map<String, List<SingleRunConfiguration>> configurationsFolders = transformRunConfigurationsIntoFoldersMap(configurationType);
        List<ConfigurationFolder> configurationFoldersList = new ArrayList<>();
        for (String folderName : configurationsFolders.keySet()) {
            List<SingleRunConfiguration> singleRunConfigurations = configurationsFolders.get(folderName);
            configurationFoldersList.add(new ConfigurationFolder(folderName, singleRunConfigurations));
        }
        return configurationFoldersList;
    }

    private static List<SingleRunConfiguration> getNonFolderedConfigurations(ConfigurationType configurationType) {
        return configurationType.getSingleRunConfigurations().stream().filter(c -> c.getFolderName() == null).collect(toList());
    }

    private static Map<String, List<SingleRunConfiguration>> transformRunConfigurationsIntoFoldersMap(ConfigurationType configurationType) {
        return configurationType.getSingleRunConfigurations().stream()
                .filter(c -> c.getFolderName() != null)
                .collect(groupingBy(SingleRunConfiguration::getFolderName));
    }

    private static Icon getTypeIcon(List<SingleRunConfiguration> singleRunConfigurations) {
        Optional<SingleRunConfiguration> anyConf = singleRunConfigurations.stream().findAny();
        return anyConf.map(SingleRunConfiguration::getTypeIcon).orElse(null);
    }
}
