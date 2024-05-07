package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FilterContainsName extends Command{
    private final CollectionManager collectionManager;
    public FilterContainsName(CollectionManager collectionManager){
        super("filter_contains_name","вывести элементы, значение поля name которых содержит заданную подстроку");
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
        List<Vehicle> vehicles = collectionManager.getVehicleMap().values()
                .stream()
                .filter(newVehicle -> newVehicle.getName().contains(args[1]))
                .collect(Collectors.toList());
        return new Response("Vehicles with specified filter:" + vehicles);
    }
}
