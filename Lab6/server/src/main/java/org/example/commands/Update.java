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

public class Update extends Command implements HasComplicatedArguments{
    private final CollectionManager collectionManager;
    public Update(CollectionManager collectionManager){
        super("update null","обновить значение элемента коллекции, ключ которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) {
        verifyArguments(args);
        if (collectionManager.checkKey(args[1])){
            int newId = collectionManager.getVehicleByKey(args[1]).getId();
            LocalDate date = collectionManager.getVehicleByKey(args[1]).getCreationDate();
            vehicle.setId(newId);
            vehicle.setCreationDate(date);
            collectionManager.add(args[1], vehicle);
            return new Response("Successfully updated");
        }
        return new Response("Non existing key");
    }

    @Override
    public String executeInScriptMode(String[] args, List<String> text) throws IOException {
        verifyArguments(args);
        if (collectionManager.checkKey(args[1])) {
            int newId = collectionManager.getVehicleByKey(args[1]).getId();
            LocalDate date = collectionManager.getVehicleByKey(args[1]).getCreationDate();
            Vehicle vehicle;
            try {
                vehicle = new Vehicle(newId, text.get(0), new Coordinates(Float.parseFloat(text.get(1)), Double.parseDouble(text.get(2))), date, Integer.valueOf(text.get(3)), VehicleType.valueOf(text.get(4)), FuelType.valueOf(text.get(5)));
            } catch (Exception e) {
                return "Not a valid object created";
            }
            if (vehicle.isValid()) {
                collectionManager.add(args[1], vehicle);
                return "Object updated";
            }
            return "Vehicle is not valid";
        }
        return "Non existing key";
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
