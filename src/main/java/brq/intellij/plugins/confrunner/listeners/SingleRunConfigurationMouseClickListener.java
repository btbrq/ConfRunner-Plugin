package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.actions.RunConfigExecutor;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationLabel;
import brq.intellij.plugins.confrunner.ui.JPanelSingleConfiguration;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SingleRunConfigurationMouseClickListener implements MouseListener {
    private final JPanelSingleConfiguration configuration;
    private final JPanelConfigurationLabel label;

    public SingleRunConfigurationMouseClickListener(JPanelConfigurationLabel label, JPanelSingleConfiguration configuration) {
        this.label = label;
        this.configuration = configuration;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        label.requestFocus();
        if (isDoubleClick(e)) {
            if (e.isControlDown()) {
                RunConfigExecutor.executeDebug(configuration.getExecutable());
            } else {
                RunConfigExecutor.executeRun(configuration.getExecutable());
            }
        }
    }

    private boolean isDoubleClick(MouseEvent e) {
        return e.getClickCount() == 2 && !e.isConsumed();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
