package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.collection.Vehicle;
import lab.managers.CollectionManager;

import java.io.IOException;

public class FilterContainsName extends Command{
    private CollectionManager collectionManager;
    public FilterContainsName(CollectionManager collectionManager){
        super("filter_contains_name","вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length>2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length==1){
            throw new NotEnoughArgumentsException();
        }
        for (Vehicle vehicle : collectionManager.getVehicleMap().values()){
            if (vehicle.getName().contains(args[1])){
                System.out.println(vehicle);
            }
        }
    }
}
