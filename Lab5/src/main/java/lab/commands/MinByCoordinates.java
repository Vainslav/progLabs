package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.collection.Vehicle;
import lab.managers.CollectionManager;

import java.io.IOException;

public class MinByCoordinates extends Command{
    private CollectionManager collectionManager;

    public MinByCoordinates(CollectionManager collectionManager){
        super("min_by_coordinates","вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        if (args.length==1){
            throw new NotEnoughArgumentsException();
        }
        Vehicle minCords = null;
        for (Vehicle vehicle : collectionManager.getVehicleMap().values()){
            if (minCords==null){
                minCords = vehicle;
            }
            else{
                if (minCords.getCoordinates().compareTo(vehicle.getCoordinates())>0){
                    minCords = vehicle;
                }
            }
        }
        System.out.println("Vehicle with min coordinates:");
        System.out.println(minCords);
    }
}
