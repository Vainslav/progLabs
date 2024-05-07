package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

import java.util.Objects;

public class Show extends Command{
    private final CollectionManager collectionManager;
    public Show(CollectionManager collectionManager){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        Response vehicles = new Response();
        collectionManager.getVehicleMap().keySet().forEach(key -> vehicles.add(String.format("%s: %n" +
                "  id: %s%n" +
                "  name: %s%n" +
                "  coordinates:%n" +
                "    x: %s%n" +
                "    y: %s%n" +
                "  creationDate: %s%n" +
                "  type: %s%n" +
                "  fuelType: %s", key, collectionManager.getVehicleByKey(key).getId(), collectionManager.getVehicleByKey(key).getName(), collectionManager.getVehicleByKey(key).getX(), collectionManager.getVehicleByKey(key).getY(), collectionManager.getVehicleByKey(key).getCreationDate(), collectionManager.getVehicleByKey(key).getType(), collectionManager.getVehicleByKey(key).getFuelType())));
        if (Objects.equals(vehicles.getData(), "")){
            return new Response("Collection is empty");
        }
        return vehicles;
    }
}
