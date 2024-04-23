package lab.managers;


import lab.commands.HasComplicatedArguments;
import lab.exceptions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleManager {
    private final CommandManager commandManager;

    public ConsoleManager(CommandManager commandManager){
        this.commandManager = commandManager;
    }
    public void run() throws IOException {
        System.out.println("Welcome to a collection manager for Vehicle objects \nType help to see all existing commands");
        Scanner userScanner = new Scanner(System.in);
        Set<String> commands = commandManager.getCommands();
        boolean running = true;
        while (running){
            String[] userInput = userScanner.nextLine().split(" ");
            try{
                if (commands.contains(userInput[0])) {
                    commandManager.execute(userInput);
                }
                else{
                    System.out.println("ERROR: Incorrect or not existing command \nTry help to see all existing commands");
                }
            }
            catch (ExitException exception){
                running = false;
            }
            catch (ExcessiveArgumentsException exception){
                System.out.println("ERROR: Excessive arguments");
            }
            catch (NotEnoughArgumentsException exception){
                System.out.println("ERROR: Not enough arguments");
            }
            catch (CollectionNotValidException exception){
                System.out.println("ERROR: Collection cannot be saved because one or more vehicles are not valid");
            }
            catch (RecursionDepthExceededException exception){
                System.out.println("ERROR: Infinite recursion in file");
            }
        }
    }
    public void executeScript(String filePath) throws IOException {
        List<String> data = Files.lines(Path.of(filePath)).collect(Collectors.toList());
        int lineNumber = 1;
        HashMap<String,Integer> recursionMap = RecursionManager.getRecursionMap();
        Integer recursionDepth = RecursionManager.getRecursionDepth();
        if (!recursionMap.containsKey(filePath)){
            recursionMap.put(filePath,recursionDepth);
        }
        for (String line: data){
            String[] args = line.split(" ");
            if (args.length>1)
                for (String key: recursionMap.keySet()){
                    if (args[1].equals(key)){
                        int recursion = recursionMap.get(key)-1;
                        if (recursion==0){
                            System.out.println("Recursion limit reached");
                            return;
                        }
                        recursionMap.put(key,recursionMap.get(key)-1);
                    }
                }
            if (commandManager.getCommands().contains(args[0])){
                if (commandManager.getCommand(args[0]) instanceof HasComplicatedArguments){
                    try {
                        commandManager.executeInScriptMode(args, data.subList(lineNumber, lineNumber + 5));
                    }
                    catch (IndexOutOfBoundsException exception){
                        System.out.println("ERROR: Not enough arguments to create an object");
                        continue;
                    }
                }
                else {
                    commandManager.executeInScriptMode(args,new ArrayList<>());
                }
            }
            lineNumber++;
        }
    }
}
