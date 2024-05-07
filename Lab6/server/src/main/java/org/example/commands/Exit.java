package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.CollectionNotValidException;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.managers.DumpManager;
import org.example.network.Response;

import java.io.IOException;

public class Exit extends Command{
    private final DumpManager dumpManager;

    public Exit(DumpManager dumpManager){
        super("exit","завершить программу (сохраняет коллекцию в файл)");
        this.dumpManager = dumpManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws IOException {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        try {
            dumpManager.dump();
        }
        catch (CollectionNotValidException e){
            return new Response("ERROR: Collection is not valid can't safely exit");
        }
        return new Response("shut down");
    }
}
