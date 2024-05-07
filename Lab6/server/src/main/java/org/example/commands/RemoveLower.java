package org.example.commands;

import org.example.collection.Coordinates;
import org.example.collection.FuelType;
import org.example.collection.Vehicle;
import org.example.collection.VehicleType;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveLower extends Command implements HasComplicatedArguments{
    private final CollectionManager collectionManager;
    public RemoveLower(CollectionManager collectionManager){
        super("remove_lower {element}","удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws IOException {
        verifyArguments(args);
        vehicle.setId(collectionManager.updateId());
        collectionManager.getVehicleMap().entrySet().removeIf(entry ->
                vehicle.compareTo(entry.getValue())>0);
        return new Response("All lower valued vehicles have been removed");
    }

    @Override
    public String executeInScriptMode(String[] args, List<String> text){
        verifyArguments(args);
        Vehicle vehicle;
        try {
            vehicle = new Vehicle(collectionManager.updateId(), text.get(0), new Coordinates(Float.valueOf(text.get(1).split(" ")[0]), Double.valueOf(text.get(1).split(" ")[1])), LocalDate.now(), Integer.valueOf(text.get(2)), VehicleType.valueOf(text.get(3)), FuelType.valueOf(text.get(4)));
        }
        catch (Exception e){
            return "Not a valid object created";
        }
        if (vehicle.isValid()){
            collectionManager.getVehicleMap().entrySet().removeIf(entry ->
                    vehicle.compareTo(entry.getValue())>0);
            return "All lower valued vehicles have been removed";
        }
        return "Vehicle is not valid";
    }
    public void verifyArguments(String[] args){
        if (args.length > 2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length != 2){
            throw new NotEnoughArgumentsException();
        }
    }
}
