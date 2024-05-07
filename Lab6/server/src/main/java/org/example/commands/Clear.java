package org.example.commands;

import org.example.collection.Vehicle;
import org.example.managers.CollectionManager;
import org.example.network.Response;

public class Clear extends Command{
    private final CollectionManager collectionManager;
    public Clear(CollectionManager collectionManager){
        super("clear","очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) {
        collectionManager.clear();
        return new Response("Collection has been cleared");
    }
}
