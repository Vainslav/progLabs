package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.managers.RecursionManager;
import org.example.managers.ScriptManager;
import org.example.network.Response;

import java.io.IOException;
import java.util.List;

public class ExecuteScript extends Command implements WorksWithFiles{


    public ExecuteScript(){
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");

    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws IOException {
        verifyArguments(args);
        Response response = new Response();
        RecursionManager.cleaRecursionMap();
        RecursionManager.setRecursionDepth(3);
        try {
            ScriptManager.startScript(args[1], response);
        }
        catch (IOException exception){
            return new Response("ERROR: File or directory doesn't exist");
        }
        response.strip();
        return response;
    }

    @Override
    public String executeInScriptMode(String[] args, List<String> text){
        verifyArguments(args);
        Response response = new Response();
        try {
            ScriptManager.startScript(args[1], response);
        }
        catch (IOException exception){
            return "ERROR: File or directory doesn't exist";
        }
        return response.getData();
    }

    public void verifyArguments(String[] args){
        if (args.length > 2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length != 2){
            throw new NotEnoughArgumentsException();
        }
    }
}
