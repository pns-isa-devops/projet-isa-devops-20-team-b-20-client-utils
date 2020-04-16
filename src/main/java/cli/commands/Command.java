package cli.commands;

import cli.framework.Shell;

import java.util.List;

/**
 * All commands should be named with the first letter in upper case and ALL the following letters in lowercase.
 */
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
