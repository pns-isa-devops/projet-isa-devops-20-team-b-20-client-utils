package cli.commands;

import cli.framework.Shell;

import java.util.List;

public abstract class Command
{
    Shell shell;

    public abstract void execute(List<String> args);

    public void setShell(Shell shell)
    {
        this.shell = shell;
    }

    abstract String help();
}
