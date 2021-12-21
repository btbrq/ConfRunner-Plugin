package brq.intellij.plugins.confrunner.ui;

import brq.intellij.plugins.confrunner.common.utils.DimensionsUtil;
import brq.intellij.plugins.confrunner.listeners.SingleRunConfigurationMouseClickListener;
import brq.intellij.plugins.confrunner.common.constants.Constants;
import brq.intellij.plugins.confrunner.listeners.ConfigurationTreeKeyNavigationListener;
import brq.intellij.plugins.confrunner.listeners.FocusListener;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.util.ui.JBUI;

import javax.swing.*;

import static brq.intellij.plugins.confrunner.ui.JPanelConfigurationLabel.createSingleConfigurationLabel;

public class JPanelSingleConfiguration extends BaseConfigurationPanel {
    private final RunnerAndConfigurationSettings executable;
    private final JPanelConfigurationLabel label;

    private JPanelSingleConfiguration(JPanelConfigurationLabel label, int offset, RunnerAndConfigurationSettings executable) {
        super(label.getLabelText());
        this.label = label;
        this.executable = executable;
        label.addFocusListener(new FocusListener(label));
        label.addMouseListener(new SingleRunConfigurationMouseClickListener(label, this));
        label.addKeyListener(new ConfigurationTreeKeyNavigationListener(this));

        label.setBorder(JBUI.Borders.emptyLeft(offset));
        add(label);
    }

    public static JPanelSingleConfiguration createSingleConfiguration(String name, Icon icon, RunnerAndConfigurationSettings executable, int offset) {
        JPanelConfigurationLabel label = createSingleConfigurationLabel(name, icon);
        JPanelSingleConfiguration configuration = new JPanelSingleConfiguration(label, offset + Constants.expandedIcon.getIconWidth() + 5, executable);

        DimensionsUtil.setLabelDimensions(configuration);
        configuration.setLayout(new BoxLayout(configuration, BoxLayout.LINE_AXIS));
        configuration.setAlignmentX(LEFT_ALIGNMENT);
        configuration.setOpaque(true);

        return configuration;
    }

    public RunnerAndConfigurationSettings getExecutable() {
        return executable;
    }
}
