package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.managers.CommandManager;

public class Help extends Command{
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help","вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        for (String key : commandManager.getCommands()){
            System.out.printf("%s: %s%n",commandManager.getCommand(key).getName(),commandManager.getCommand(key).getDescription());
        }
    }
}
