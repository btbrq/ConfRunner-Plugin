package brq.intellij.plugins.confrunner.dialog;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class AppDialogAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        AppDialog dialog = new AppDialog(project);
        DialogState instance = DialogState.getInstance();
        instance.setDialog(dialog);
        dialog.showAndGet();
    }
}
