package client.utils.cli.commands;

import java.util.List;

public class Bye extends Command {
    @Override
    public void execute(List<String> args) {
        System.out.println("Bye...");
        System.exit(0);
    }

    @Override
    protected String help() {
        return "Quit app";
    }
}
