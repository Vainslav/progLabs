package org.example.commands;

import org.example.collection.Coordinates;
import org.example.collection.FuelType;
import org.example.collection.Vehicle;
import org.example.collection.VehicleType;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

import java.time.LocalDate;
import java.util.List;

public class Insert extends Command implements HasComplicatedArguments{
    private final CollectionManager collectionManager;
    public Insert(CollectionManager collectionManager){
        super("insert null {element}", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) {
        verifyArguments(args);
        vehicle.setId(collectionManager.updateId());
        if (!collectionManager.checkKey(args[1])) {
            collectionManager.add(args[1], vehicle);
        } else {
            return new Response(String.format("ERROR: object with id: %s already exists%n", args[1]));
        }
        return new Response("Object added to the collection");
    }

    @Override
    public String executeInScriptMode(String[] args, List<String> text){
        verifyArguments(args);
        Vehicle newVehicle;
        try {
            newVehicle = new Vehicle(collectionManager.updateId(), text.get(0), new Coordinates(Float.valueOf(text.get(1).split(" ")[0]), Double.valueOf(text.get(1).split(" ")[1])), LocalDate.now(), Integer.valueOf(text.get(2)), VehicleType.valueOf(text.get(3)), FuelType.valueOf(text.get(4)));
        }
        catch (Exception exception){
            return "ERROR: Not a valid object created";
        }
        if (!collectionManager.checkKey(args[1])) {
            collectionManager.add(args[1], newVehicle);
            return "Object added to the collection";
        }
        return String.format("ERROR: Object with id: %s already exists",args[1]);
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
