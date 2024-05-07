package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

import java.io.IOException;

public class AverageOfEnginePower extends Command{
    private final CollectionManager collectionManager;
    public AverageOfEnginePower(CollectionManager collectionManager){
        super("average_of_engine_power","вывести среднее значение поля enginePower для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws IOException {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        Integer combinedEnginePower = collectionManager.getVehicleMap().values().stream().mapToInt(Vehicle::getEnginePower).sum();
        if (collectionManager.getSize()!=0) {
            return new Response("Average engine power of all Vehicles: " + combinedEnginePower / collectionManager.getSize());
        }
        return new Response("Collection is empty\n");
    }
}
