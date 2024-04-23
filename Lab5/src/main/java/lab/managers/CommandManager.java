package lab.managers;

import lab.commands.Command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    public void newCommand(String commandName, Command command){
        commands.put(commandName, command);
    }

    public Set<String> getCommands(){
        return commands.keySet();
    }

    public Command getCommand(String key){
        return commands.get(key);
    }

    public void execute(String[] command) throws IOException {
        commands.get(command[0]).execute(command);
    }

    public void executeInScriptMode(String[] command, List<String> text) throws IOException {
        commands.get(command[0]).executeInScriptMode(command, text);
    }
}
