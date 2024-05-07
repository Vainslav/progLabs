package org.example.managers;

import org.example.collection.Vehicle;
import org.example.commands.Command;
import org.example.network.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CommandManager {
    private static final HashMap<String, Command> commands = new HashMap<>();
    public static void newCommand(String commandName, Command command){
        commands.put(commandName, command);
    }

    public static Set<String> getCommands(){
        return commands.keySet();
    }

    public static Command getCommand(String key){
        return commands.get(key);
    }

    public static Response execute(String[] command, Vehicle vehicle) throws IOException {
        return commands.get(command[0]).execute(command, vehicle);
    }

    public static String executeInScriptMode(String[] command, List<String> text) throws IOException {
        return commands.get(command[0]).executeInScriptMode(command, text);
    }
}
