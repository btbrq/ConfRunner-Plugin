package brq.intellij.plugins.confrunner.dialog;

import brq.intellij.plugins.confrunner.ui.JPanelConfigurationLabel;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTypeBox;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogState {
    private static DialogState instance;
    private AppDialog dialog;

    public static DialogState getInstance() {
        if (instance == null) {
            instance = new DialogState();
        }
        return instance;
    }

    public void closeDialog() {
        dialog.close(0, true);
    }

    public void setDialog(AppDialog dialog) {
        this.dialog = dialog;
    }

    public List<JPanelConfigurationTypeBox> getConfigurationTypeBoxes() {
        return dialog.getConfigurationTree().getConfigurationTypeBoxes();
    }

    public void scrollToTop() {
        dialog.getScrollPane().getVerticalScrollBar().setValue(0);
    }

    public void scrollToBottom() {
        JScrollBar vertical = dialog.getScrollPane().getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    public void scrollUp(JPanelConfigurationLabel label) {
        Rectangle bounds = label.getBounds();
        int labelHeight = label.getHeight() * 3;
        bounds.y = bounds.y - labelHeight;
        label.scrollRectToVisible(bounds);
    }

    public void scrollDown(JPanelConfigurationLabel label) {
        Rectangle bounds = label.getBounds();
        int labelHeight = label.getHeight() * 3;
        bounds.y = bounds.y + labelHeight;
        label.scrollRectToVisible(bounds);
    }

    public void focusConfigurationsLookup() {
        dialog.getConfigurationsLookup().requestFocus();
    }

}
