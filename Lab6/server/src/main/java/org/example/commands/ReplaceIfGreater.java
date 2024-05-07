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
import java.util.List;

public class ReplaceIfGreater extends Command implements HasComplicatedArguments{
    private final CollectionManager collectionManager;
    public ReplaceIfGreater(CollectionManager collectionManager){
        super("replace_if_greater null {element}", "заменить значение по ключу, если новое значение больше старого");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) throws IOException {
        verifyArguments(args);
        vehicle.setId(collectionManager.updateId());
        if (vehicle.compareTo(collectionManager.getVehicleByKey(args[1]))>0){
            collectionManager.add(args[1], vehicle);
            return new Response("Vehicle successfully replaced");
        }
        return new Response("Your Vehicle is smaller and wasn't replaced");
    }

    @Override
    public String executeInScriptMode(String[] args, List<String> text){
        Vehicle newVehicle;
        try {
            newVehicle = new Vehicle(collectionManager.updateId(), text.get(0), new Coordinates(Float.valueOf(text.get(1).split(" ")[0]), Double.valueOf(text.get(1).split(" ")[1])), LocalDate.now(), Integer.valueOf(text.get(2)), VehicleType.valueOf(text.get(3)), FuelType.valueOf(text.get(4)));
        }
        catch (Exception exception){
            return "ERROR: Not a valid object created";
        }
        if (!collectionManager.checkKey(args[1])){
            return "ERROR: Non existing key";
        }
        if (newVehicle.compareTo(collectionManager.getVehicleByKey(args[1]))>0){
            collectionManager.add(args[1],newVehicle);
            return "Vehicle successfully replaced";
        }
        return "Your Vehicle is smaller and wasn't replaced";
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
