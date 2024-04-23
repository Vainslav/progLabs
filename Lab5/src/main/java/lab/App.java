package lab;

import lab.commands.*;
import lab.managers.CollectionManager;
import lab.managers.CommandManager;
import lab.managers.ConsoleManager;
import lab.managers.DumpManager;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        ConsoleManager consoleManager = new ConsoleManager(commandManager);
        DumpManager dumpManager = new DumpManager(collectionManager);
        commandManager.newCommand("help",new Help(commandManager));
        commandManager.newCommand("info",new Info(collectionManager));
        commandManager.newCommand("show",new Show(collectionManager));
        commandManager.newCommand("insert",new Insert(collectionManager));
        commandManager.newCommand("update",new Update(collectionManager));
        commandManager.newCommand("remove_key",new RemoveKey(collectionManager));
        commandManager.newCommand("clear",new Clear(collectionManager));
        commandManager.newCommand("save",new Save(dumpManager));
        commandManager.newCommand("exit",new Exit());
        commandManager.newCommand("execute_script",new ExecuteScript(consoleManager));
        commandManager.newCommand("remove_lower",new RemoveLower(collectionManager));
        commandManager.newCommand("replace_if_greater", new ReplaceIfGreater(collectionManager));
        commandManager.newCommand("remove_lower_key", new RemoveLowerKey(collectionManager));
        commandManager.newCommand("average_of_engine_power", new AverageOfEnginePower(collectionManager));
        commandManager.newCommand("min_by_coordinates",new MinByCoordinates(collectionManager));
        commandManager.newCommand("filter_contains_name",new FilterContainsName(collectionManager));
        if (args.length>0){
            dumpManager.setFile(args[0]);
        }
        else{
            dumpManager.setFile("collection");
        }
        dumpManager.load();
        consoleManager.run();
    }
}