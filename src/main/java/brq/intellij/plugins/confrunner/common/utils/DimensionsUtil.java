package brq.intellij.plugins.confrunner.common.utils;

import brq.intellij.plugins.confrunner.ui.ConfigurationsLookup;
import brq.intellij.plugins.confrunner.common.constants.Constants;

import javax.swing.*;
import java.awt.*;

public class DimensionsUtil {

    public static void setLabelDimensions(JPanel label) {
//        label.setMinimumSize(dimension(DIALOG_MIN_WIDTH, CONFIGURATION_ITEM_HEIGHT));
//        label.setMaximumSize(dimension(desktopWidth(), CONFIGURATION_ITEM_HEIGHT));
    }

    public static void setLookupDimensions(ConfigurationsLookup lookup) {
        lookup.setMinimumSize(dimension(Constants.DIALOG_MIN_WIDTH, Constants.CONFIGURATION_ITEM_HEIGHT));
        lookup.setMaximumSize(dimension(desktopWidth(), Constants.expandedIcon.getIconHeight()));
    }

    public static void setDialogDimensions(JPanel dialog) {
        dialog.setMinimumSize(dimension(Constants.DIALOG_MIN_WIDTH, Constants.DIALOG_MIN_HEIGHT));
        dialog.setMaximumSize(dimension(desktopWidth(), desktopHeight()));
        dialog.setPreferredSize(dimension(Constants.DIALOG_PREFERRED_WIDTH, Constants.DIALOG_PREFERRED_HEIGHT));
    }

    private static Dimension dimension(int width, int height) {
        return new Dimension(width, height);
    }

    public static int desktopWidth() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    private static int desktopHeight() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}
