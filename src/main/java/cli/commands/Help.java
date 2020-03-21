package cli.commands;

import java.util.List;

public class Help extends Command
{

    @Override
    public void execute(List<String> args)
    {
        System.out.println("List of command :");
        this.shell.getCommands().forEach(command -> System.out.println("\t" + command.getClass().getSimpleName().toLowerCase() + " : " + command.help()));
    }

    @Override
    String help()
    {
        return "Print help";
    }
}
