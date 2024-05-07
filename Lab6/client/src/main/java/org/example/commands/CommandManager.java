package org.example.commands;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CommandManager {
    private static HashMap<String,String> commands =new HashMap<>();
    public void put(String commandName, String additionalInfo){
        commands.put(commandName, additionalInfo);
    }

    public static void setCommands(HashMap<String,String> commands){
        CommandManager.commands = commands;
    }

    public static HashMap<String,String> getCommands(){
        return commands;
    }
}
