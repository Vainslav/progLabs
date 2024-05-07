package org.example.managers;

import org.example.commands.HasComplicatedArguments;
import org.example.network.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ScriptManager {
    public static void startScript(String filePath, Response toWriteIn) throws IOException{
        List<String> data = Files.lines(Path.of(filePath)).collect(Collectors.toList());
        int lineNumber = 1;
        HashMap<String,Integer> recursionMap = RecursionManager.getRecursionMap();
        Integer recursionDepth = RecursionManager.getRecursionDepth();
        System.out.println(recursionDepth);
        if (!recursionMap.containsKey(filePath)){
            recursionMap.put(filePath,recursionDepth);
        }
        for (String line: data){
            String[] args = line.split(" ");
            if (args.length>1)
                for (String key: recursionMap.keySet()){
                    if (args[1].equals(key)){
                        System.out.println(recursionMap);
                        int recursion = recursionMap.get(key)-1;
                        if (recursion==0){
                            toWriteIn.add("Recursion limit reached");
                            return;
                        }
                        recursionMap.put(key,recursionMap.get(key)-1);
                    }
                }
            if (CommandManager.getCommands().contains(args[0])){
                if (CommandManager.getCommand(args[0]) instanceof HasComplicatedArguments){
                    try {
                        String message = CommandManager.executeInScriptMode(args, data.subList(lineNumber, lineNumber + 5));
                        System.out.println(message);
                        if (message != null) {
                            toWriteIn.add(message);
                        }
                    }
                    catch (IndexOutOfBoundsException exception){
                        toWriteIn.add("ERROR Not enough arguments to create an object");
                        continue;
                    }
                }
                else {
                    String message = CommandManager.executeInScriptMode(args,new ArrayList<>());
                    System.out.println(message);
                    if (message != null){
                        toWriteIn.add(message);
                    }
                }

            }
            lineNumber++;
        }
    }
}
