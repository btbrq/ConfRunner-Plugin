package brq.intellij.plugins.confrunner.listeners;

import brq.intellij.plugins.confrunner.ui.JPanelChildrenToggle;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickListener implements MouseListener {
    private final JPanelChildrenToggle configuration;
    private final JPanelConfigurationLabel label;

    public MouseClickListener(JPanelChildrenToggle configuration, JPanelConfigurationLabel label) {
        this.configuration = configuration;
        this.label = label;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        label.requestFocus();
        if (isDoubleClick(e)) {
            configuration.toggle();
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
