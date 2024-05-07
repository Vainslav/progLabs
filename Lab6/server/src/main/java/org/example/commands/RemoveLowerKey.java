package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

import java.io.IOException;

public class RemoveLowerKey extends Command{
    private final CollectionManager collectionManager;
    public RemoveLowerKey(CollectionManager collectionManager){
        super("remove_lower_key null","удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws IOException {
        if (args.length>2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length==1){
            throw new NotEnoughArgumentsException();
        }
        collectionManager.getVehicleMap().keySet()
                .removeIf(key -> args[0].compareTo(key)<=0);
        return new Response("Vehicles with lower keys have been removed");
    }
}
