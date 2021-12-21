package brq.intellij.plugins.confrunner.dialog;

import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTypeBox;

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

    public void focusConfigurationsLookup() {
        dialog.getConfigurationsLookup().requestFocus();
    }

}
