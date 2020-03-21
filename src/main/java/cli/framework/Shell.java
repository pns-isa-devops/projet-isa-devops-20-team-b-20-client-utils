package cli.framework;

import api.API;
import cli.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Shell
{


    private List<Command> commands;

    private API droneDeliveryAPI;

    public Shell(API droneDeliveryAPI)
    {
        this.droneDeliveryAPI = droneDeliveryAPI;
    }

    public API getDroneDeliveryAPI()
    {
        return droneDeliveryAPI;
    }

    public List<Command> getCommands()
    {
        return commands;
    }

    @SafeVarargs
    public final void register(Class<? extends Command>... commandsClass) throws IllegalAccessException, InstantiationException
    {
        this.commands = new ArrayList<>();
        for (Class<? extends Command> command : commandsClass)
        {
            this.commands.add(command.newInstance());
        }
    }


    public void run()
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.flush();
            if (!scanner.hasNext())
            {
                System.out.println("Reaching end of file");
                break;
            }

            String keyword = scanner.next().trim();

            List<String> args = new ArrayList<>();

            if (scanner.hasNextLine())
            {
                args = Arrays.stream(scanner.nextLine().split(" ")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
            }

            executeCommand(keyword, args);
        }
    }

    private void executeCommand(String keyword, List<String> args)
    {
        try
        {
            String commandName = keyword.toLowerCase();
            commandName = commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
            Class<? extends Command> command = (Class<? extends Command>) Class.forName("cli.commands." + commandName);
            Command c = command.newInstance();
            c.setShell(this);
            c.execute(args);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            System.err.println("Command " + keyword + " cannot be instanciate");
        }
    }
}
