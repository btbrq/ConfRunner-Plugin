package brq.intellij.plugins.confrunner.test.utils

import brq.intellij.plugins.confrunner.domain.ConfigurationFolder
import brq.intellij.plugins.confrunner.domain.ConfigurationTree
import brq.intellij.plugins.confrunner.domain.ConfigurationTypeFoldered
import brq.intellij.plugins.confrunner.domain.SingleRunConfiguration
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTree
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTypeFoldersAndSingleConfigurations

import static java.util.stream.Collectors.toList

class ConfigurationMarshaller {

    static def mapVisibleUIComponentsToDomain(JPanelConfigurationTree tree) {
        def types = tree.getConfigurationTypeBoxes().stream()
                .filter { t -> t.isVisible() && t.getFoldersAndSingleConfigurations().isVisible() }
                .map { t ->
                    def foldersAndConfs = t.getFoldersAndSingleConfigurations()
                    def nonFolderedConfs = transformToSingleConfigurations(foldersAndConfs, t)
                    def folderedConfs = transformToFolderedConfigurations(foldersAndConfs, t)
                    return new ConfigurationTypeFoldered(t.getLabelText(), null, folderedConfs, nonFolderedConfs)
                }
                .collect(toList())
        return new ConfigurationTree(types)
    }

    private static def transformToSingleConfigurations(JPanelConfigurationTypeFoldersAndSingleConfigurations foldersAndConfs, t) {
        foldersAndConfs.getNonFolderedConfigurationsPanel().stream()
                .filter { c -> c.isVisible() }
                .map { c -> new SingleRunConfiguration(c.getLabelText(), null, t.getLabelText(), null, null, null) }
                .collect(toList())
    }

    private static def transformToFolderedConfigurations(JPanelConfigurationTypeFoldersAndSingleConfigurations foldersAndConfs, t) {
        foldersAndConfs.getFolderedConfigurationsPanel().stream()
                .filter { f -> f.isVisible() && f.getConfigurations().isVisible() }
                .map { f ->
                    def confs = f.getConfigurations().getConfigurations().stream()
                            .filter { c -> c.isVisible() }
                            .map { c -> new SingleRunConfiguration(c.getLabelText(), null, t.getLabelText(), null, f.getLabelText(), null) }
                            .collect(toList())
                    return new ConfigurationFolder(f.getLabelText(), confs)
                }.collect(toList())
    }
}
