package brq.intellij.plugins.confrunner.actions;

import brq.intellij.plugins.confrunner.dialog.ConfigurationRunTypeUnsupportedDialog;
import brq.intellij.plugins.confrunner.dialog.DialogState;
import com.intellij.execution.Executor;
import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.openapi.ui.DialogWrapper;

public class RunConfigExecutor {

    public static void executeDebug(RunnerAndConfigurationSettings executable) {
        execute(DefaultDebugExecutor.EXECUTOR_ID, executable, "Debug", DefaultDebugExecutor.getDebugExecutorInstance());
    }

    public static void executeRun(RunnerAndConfigurationSettings executable) {
        execute(DefaultRunExecutor.EXECUTOR_ID, executable, "Run", DefaultRunExecutor.getRunExecutorInstance());
    }

    private static void execute(String executorId, RunnerAndConfigurationSettings executable, String message, Executor executor) {
        ProgramRunner<RunnerSettings> runner = ProgramRunner.getRunner(executorId, executable.getConfiguration());
        if (runner != null && runner.canRun(executorId, executable.getConfiguration())) {
            ProgramRunnerUtil.executeConfiguration(executable, executor);
            DialogState.getInstance().closeDialog();
        } else {
            DialogWrapper dialog = new ConfigurationRunTypeUnsupportedDialog(message);
            dialog.show();
        }
    }

}
