package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

import java.io.IOException;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinByCoordinates extends Command{
    private final CollectionManager collectionManager;
    public MinByCoordinates(CollectionManager collectionManager){
        super("min_by_coordinates","вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws IOException {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        Vehicle minCords = collectionManager.getVehicleMap().values().stream().min(Comparator.comparing(Vehicle::getCoordinates)).orElseThrow(NoSuchElementException::new);
        return new Response("Vehicle with min coordinates: " + minCords);
    }
}
