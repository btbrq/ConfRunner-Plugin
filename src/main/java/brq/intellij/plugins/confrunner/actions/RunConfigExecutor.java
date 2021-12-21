package brq.intellij.plugins.confrunner.actions;

import brq.intellij.plugins.confrunner.dialog.DialogState;
import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.executors.DefaultRunExecutor;

public class RunConfigExecutor {
    public static void executeDebug(RunnerAndConfigurationSettings executable) {
        ProgramRunnerUtil.executeConfiguration(executable, DefaultDebugExecutor.getDebugExecutorInstance());
        DialogState.getInstance().closeDialog();
    }

    public static void executeRun(RunnerAndConfigurationSettings executable) {
        ProgramRunnerUtil.executeConfiguration(executable, DefaultRunExecutor.getRunExecutorInstance());
        DialogState.getInstance().closeDialog();
    }
}
