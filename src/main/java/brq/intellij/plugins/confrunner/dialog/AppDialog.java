package brq.intellij.plugins.confrunner.dialog;

import brq.intellij.plugins.confrunner.builders.JPanelConfigurationTreeBuilder;
import brq.intellij.plugins.confrunner.common.utils.DimensionsUtil;
import brq.intellij.plugins.confrunner.settings.Settings;
import brq.intellij.plugins.confrunner.ui.ConfigurationsLookup;
import brq.intellij.plugins.confrunner.ui.JBConfigurationTreeScrollPane;
import brq.intellij.plugins.confrunner.ui.JPanelConfigurationTree;
import com.intellij.execution.impl.RunManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static brq.intellij.plugins.confrunner.common.constants.Constants.APP_NAME;

public class AppDialog extends DialogWrapper {
    private final Project project;
    private JPanelConfigurationTree configurationTree;
    private ConfigurationsLookup configurationsLookup;
    private JBConfigurationTreeScrollPane scrollPane;

    public AppDialog(@Nullable Project project) {
        super(true);
        this.project = project;
        setTitle(APP_NAME);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel();
        DimensionsUtil.setDialogDimensions(dialogPanel);
        dialogPanel.setLayout((new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS)));
        RunManagerImpl instanceImpl = RunManagerImpl.getInstanceImpl(project);

        configurationTree = JPanelConfigurationTreeBuilder.getJPanelConfigurationTree(instanceImpl, Settings.getInstance());
        configurationsLookup = ConfigurationsLookup.createLookup();

        scrollPane = JBConfigurationTreeScrollPane.createConfigurationTreeScrollPane(configurationTree);

        dialogPanel.add(configurationsLookup);
        dialogPanel.add(scrollPane);

        return dialogPanel;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return configurationsLookup;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        super.createDefaultActions();
        return new Action[]{};
    }

    public JPanelConfigurationTree getConfigurationTree() {
        return configurationTree;
    }

    public ConfigurationsLookup getConfigurationsLookup() {
        return configurationsLookup;
    }

    public JBConfigurationTreeScrollPane getScrollPane() {
        return scrollPane;
    }
}
