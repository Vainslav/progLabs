package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

public class RemoveKey extends Command{
    private final CollectionManager collectionManager;
    public RemoveKey(CollectionManager collectionManager){
        super("remove_key null","удалить элемент из коллекции по его ключу");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) {
        if (args.length>2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length==1) {
            throw new NotEnoughArgumentsException();
        }
        if (collectionManager.checkKey(args[1])){
            collectionManager.remove(args[1]);
            return new Response(String.format("Vehicle with key %s has been successfully removed", args[1]));
        }
        return new Response("Non existing key");
    }
}
