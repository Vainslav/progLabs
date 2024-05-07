package org.example.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.managers.CommandManager;
import org.example.network.Response;

public class Help extends Command{
    public Help() {
        super("help","вывести справку по доступным командам");
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws JsonProcessingException {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        Response response = new Response();
        CommandManager.getCommands().forEach(command -> response.add(command+": "+CommandManager.getCommand(command).getDescription()));
        return response;
    }
}
