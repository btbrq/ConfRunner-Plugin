package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.common.constants.Constants;
import brq.intellij.plugins.confrunner.common.utils.DimensionsUtil;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class JPanelConfigurationLabel extends JPanel {
    private String labelText;
    private JLabel expandCollapseIcon;
    private JLabel iconLabel;
    private JLabel nameLabel;

    public void toggleExpanded() {
        expandCollapseIcon.setIcon(Constants.expandedIcon);
        iconLabel.setBorder(JBUI.Borders.empty(5, Constants.collapsedIcon.getIconWidth(), 5, 0));
    }

    public void toggleCollapsed() {
        expandCollapseIcon.setIcon(Constants.collapsedIcon);
        iconLabel.setBorder(JBUI.Borders.empty(5, Constants.expandedIcon.getIconWidth(), 5, 0));
    }

    private JPanelConfigurationLabel(String label, Icon labelIcon, boolean applyExpandCollapse) {
        super();
        labelText = label;
        DimensionsUtil.setLabelDimensions(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        if (applyExpandCollapse) {
            expandCollapseIcon = new JLabel(Constants.expandedIcon);
            expandCollapseIcon.setBorder(JBUI.Borders.empty(5, 5, 5, 0));
            add(expandCollapseIcon);
        }

        iconLabel = new JLabel(labelIcon);
        iconLabel.setBorder(JBUI.Borders.empty(5, Constants.collapsedIcon.getIconWidth(), 5, 0));

        nameLabel = new JLabel(label);
        nameLabel.setBorder(JBUI.Borders.empty(5, 5, 5, DimensionsUtil.desktopWidth()));

        add(iconLabel);
        add(nameLabel);
    }

    public static JPanelConfigurationLabel createSingleConfigurationLabel(String label, Icon labelIcon) {
        return new JPanelConfigurationLabel(label, labelIcon, false);
    }

    public static JPanelConfigurationLabel createTypeFolderLabel(String label, Icon labelIcon) {
        return new JPanelConfigurationLabel(label, labelIcon, true);
    }

    public String getLabelText() {
        return labelText;
    }
}
