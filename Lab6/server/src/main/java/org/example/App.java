package org.example;

import org.example.commands.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.ConsoleManager;
import org.example.managers.DumpManager;
import org.example.network.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        CollectionManager collectionManager = new CollectionManager();
        DumpManager dumpManager = new DumpManager(collectionManager);
        CommandManager.newCommand("help",new Help());
        CommandManager.newCommand("info",new Info(collectionManager));
        CommandManager.newCommand("show",new Show(collectionManager));
        CommandManager.newCommand("insert",new Insert(collectionManager));
        CommandManager.newCommand("update",new Update(collectionManager));
        CommandManager.newCommand("remove_key",new RemoveKey(collectionManager));
        CommandManager.newCommand("clear",new Clear(collectionManager));
        CommandManager.newCommand("exit",new Exit(dumpManager));
        CommandManager.newCommand("execute_script",new ExecuteScript());
        CommandManager.newCommand("remove_lower",new RemoveLower(collectionManager));
        CommandManager.newCommand("replace_if_greater", new ReplaceIfGreater(collectionManager));
        CommandManager.newCommand("remove_lower_key", new RemoveLowerKey(collectionManager));
        CommandManager.newCommand("average_of_engine_power", new AverageOfEnginePower(collectionManager));
        CommandManager.newCommand("min_by_coordinates",new MinByCoordinates(collectionManager));
        CommandManager.newCommand("filter_contains_name",new FilterContainsName(collectionManager));
        SocketAddress address = new InetSocketAddress("localhost", 2769);
        Server server = new Server(address);
        dumpManager.setFile("collection");
        dumpManager.load();
        ConsoleManager consoleManager = new ConsoleManager(dumpManager, collectionManager);
        consoleManager.start();
        server.run();
    }
}
