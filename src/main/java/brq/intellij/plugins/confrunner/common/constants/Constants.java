package brq.intellij.plugins.confrunner.common.constants;

import javax.swing.*;

import static com.intellij.icons.AllIcons.Nodes.Folder;

public final class Constants {
    public static final String APP_NAME = "ConfRunner";
    public static final int DIALOG_MIN_WIDTH = 300;
    public static final int DIALOG_PREFERRED_WIDTH = 500;
    public static final int DIALOG_MIN_HEIGHT = 200;
    public static final int DIALOG_PREFERRED_HEIGHT = 300;
    public static final int CONFIGURATION_ITEM_HEIGHT = 25;
    public static final int CONFIGURATION_ITEM_WIDTH = 400;
    public static final int TYPE_CHILDREN_OFFSET = 20;
    public static final int FOLDER_CHILDREN_OFFSET = 40;

    public static Icon expandedIcon = (Icon) UIManager.get("Tree.expandedIcon");
    public static Icon collapsedIcon = (Icon) UIManager.get("Tree.collapsedIcon");
    public static int iconWidth = Folder.getIconWidth() + 5;
    public static int folderIconWidth = Folder.getIconWidth() * 2 + 10;
}
