package brq.intellij.plugins.confrunner.dialog;

import brq.intellij.plugins.confrunner.common.utils.DimensionsUtil;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ConfigurationRunTypeUnsupportedDialog extends DialogWrapper {
    private final String message;
    private JPanel dialogPanel;

    public ConfigurationRunTypeUnsupportedDialog(String message) {
        super(true);
        this.message = message;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        DimensionsUtil.setConfigurationRunTypeUnsupportedDialogDimensions(dialogPanel);
        String text = "<html>This configuration can't be run in " + message + " mode. Try using different mode.</html>";
        JLabel label = new JLabel(text);
        dialogPanel.add(label, BorderLayout.CENTER);
        return dialogPanel;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return dialogPanel;
    }

}
