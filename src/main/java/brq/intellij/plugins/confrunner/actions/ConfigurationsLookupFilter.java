package brq.intellij.plugins.confrunner.actions;

import brq.intellij.plugins.confrunner.ui.JPanelConfigurationFolderBox;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTypeBox;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTypeFoldersAndSingleConfigurations;
import brq.intellij.plugins.confrunner.ui.JPanelSingleConfiguration;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConfigurationsLookupFilter {

    public static void filterConfigurations(List<JPanelConfigurationTypeBox> types, String text) {
        if (StringUtils.isBlank(text)) {
            makeAllConfigurationsVisible(types);
        } else {
            performFiltering(types, text);
        }
    }

    private static void makeAllConfigurationsVisible(List<JPanelConfigurationTypeBox> types) {
        types.forEach(ConfigurationsLookupFilter::makeConfigurationTypeChildrenVisible);
    }

    private static void performFiltering(List<JPanelConfigurationTypeBox> types, String text) {
        types.forEach(c -> {
            if (configurationTypeNameMatches(text, c)) {
                makeConfigurationTypeChildrenVisible(c);
            } else {
                makeConfigurationsVisibleIfMatching(c, text);
            }
        });
    }

    private static void makeConfigurationsVisibleIfMatching(JPanelConfigurationTypeBox type, String text) {
        JPanelConfigurationTypeFoldersAndSingleConfigurations foldersAndSingleConfs = type.getFoldersAndSingleConfigurations();
        List<JPanelConfigurationFolderBox> folders = foldersAndSingleConfs.getFolderedConfigurationsPanel();
        List<JPanelSingleConfiguration> nonFolderedConfs = foldersAndSingleConfs.getNonFolderedConfigurationsPanel();

        if (atLeastOneConfigurationMatches(text, nonFolderedConfs) || atLeastOneFolderMatching(text, folders)) {
            type.setVisible(true);
            foldersAndSingleConfs.setVisible(true);
        }

        nonFolderedConfs.forEach(sc -> sc.setVisible(labelMatches(sc.getLabelText(), text)));

        folders.forEach(f -> {
            if (labelMatches(f.getLabelText(), text)) {
                makeEverythingWithinFolderVisible(f);
            } else if (atLeaseOnConfigurationUnderFolderMatches(text, f)) {
                f.setVisible(true);
                f.getConfigurations().setVisible(true);
                f.getConfigurations().getConfigurations().forEach(sc -> sc.setVisible(labelMatches(sc.getLabelText(), text)));
            } else {
                f.setVisible(false);
                f.getConfigurations().setVisible(false);
                f.getConfigurations().getConfigurations().forEach(sc -> sc.setVisible(false));
            }
        });

        if (!atLeastOneFolderMatching(text, folders) && !atLeastOneConfigurationMatches(text, nonFolderedConfs)) {
            type.setVisible(false);
            foldersAndSingleConfs.setVisible(false);
        }
    }

    private static void makeConfigurationTypeChildrenVisible(JPanelConfigurationTypeBox c) {
        c.setVisible(true);
        JPanelConfigurationTypeFoldersAndSingleConfigurations foldersAndSingleConfs = c.getFoldersAndSingleConfigurations();
        foldersAndSingleConfs.setVisible(true);
        foldersAndSingleConfs.getFolderedConfigurationsPanel().forEach(ConfigurationsLookupFilter::makeEverythingWithinFolderVisible);
        foldersAndSingleConfs.getNonFolderedConfigurationsPanel().forEach(sc -> sc.setVisible(true));
    }

    private static void makeEverythingWithinFolderVisible(JPanelConfigurationFolderBox folder) {
        folder.setVisible(true);
        folder.getConfigurations().setVisible(true);
        folder.getConfigurations().getConfigurations().forEach(sc -> sc.setVisible(true));
    }

    private static boolean atLeastOneFolderMatching(String text, List<JPanelConfigurationFolderBox> folders) {
        return atLeastOneFolderNameMatches(text, folders) || atLeaseOneConfigurationUnderFoldersMatches(text, folders);
    }

    private static boolean atLeastOneFolderNameMatches(String text, List<JPanelConfigurationFolderBox> folders) {
        return folders.stream().anyMatch(f -> labelMatches(f.getLabelText(), text));
    }

    private static boolean atLeaseOneConfigurationUnderFoldersMatches(String text, List<JPanelConfigurationFolderBox> folders) {
        return folders.stream().anyMatch(f -> atLeaseOnConfigurationUnderFolderMatches(text, f));
    }

    private static boolean atLeaseOnConfigurationUnderFolderMatches(String text, JPanelConfigurationFolderBox folder) {
        return atLeastOneConfigurationMatches(text, folder.getConfigurations().getConfigurations());
    }

    private static boolean atLeastOneConfigurationMatches(String text, List<JPanelSingleConfiguration> configurations) {
        return configurations.stream().anyMatch(sc -> labelMatches(sc.getLabelText(), text));
    }

    private static boolean configurationTypeNameMatches(String text, JPanelConfigurationTypeBox c) {
        return labelMatches(c.getLabelText(), text);
    }

    private static boolean labelMatches(String label, String text) {
        String regex = Arrays.stream(text.split("(?=\\p{Lu})")).map(String::toLowerCase).collect(Collectors.joining(".*"));
        return Pattern.matches(".*" + regex + ".*", label.toLowerCase());
    }
}
