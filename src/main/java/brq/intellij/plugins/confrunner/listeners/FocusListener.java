package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.ui.JPanelConfigurationLabel;
import com.intellij.util.ui.UIUtil;

import java.awt.event.FocusEvent;

public class FocusListener implements java.awt.event.FocusListener {
    private final JPanelConfigurationLabel label;

    public FocusListener(JPanelConfigurationLabel label) {
        this.label = label;
    }

    @Override
    public void focusGained(FocusEvent e) {
        label.setBackground(UIUtil.getTreeSelectionBackground(true));
    }

    @Override
    public void focusLost(FocusEvent e) {
        label.setBackground(UIUtil.getTreeBackground());
    }
}
