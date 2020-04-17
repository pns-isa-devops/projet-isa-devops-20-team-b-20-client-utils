package client.utils.cli.commands;

import java.util.List;

import client.utils.cli.framework.Shell;

/**
 * All commands should be named with the first letter in upper case and ALL the
 * following letters in lowercase.
 */
public abstract class Command {
    protected Shell shell;

    public abstract void execute(List<String> args);

    public void setShell(Shell shell) {
        this.shell = shell;
    }

    protected abstract String help();
}
