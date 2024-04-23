package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.managers.ConsoleManager;
import lab.managers.RecursionManager;

import java.io.IOException;
import java.util.List;

public class ExecuteScript extends Command{
    private ConsoleManager consoleManager;

    public ExecuteScript(ConsoleManager consoleManager){
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.consoleManager = consoleManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        verifyArguments(args);
        try {
            RecursionManager.cleaRecursionMap();
            RecursionManager.setRecursionDepth(3);
            consoleManager.executeScript(args[1]);
        }
        catch (IOException exception){
            System.out.println("ERROR: File or directory doesn't exist");
        }
    }

    @Override
    public void executeInScriptMode(String[] args, List<String> text){
        verifyArguments(args);
        try {
            consoleManager.executeScript(args[1]);
        }
        catch (IOException exception){
            System.out.println("ERROR: File or directory doesn't exist");
        }
    }

    public void verifyArguments(String[] args){
        if (args.length>2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length==1){
            throw new NotEnoughArgumentsException();
        }
    }
}
