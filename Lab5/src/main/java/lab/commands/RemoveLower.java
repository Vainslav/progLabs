package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.IncorrectCreationException;
import lab.collection.Coordinates;
import lab.collection.FuelType;
import lab.collection.Vehicle;
import lab.collection.VehicleType;
import lab.managers.CollectionManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveLower extends Command implements HasComplicatedArguments{
    private CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager){
        super("remove_lower {element}","удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        verifyArguments(args);
        Set<String> keysToRemove = new HashSet<>();
        Vehicle vehicleToCompare;
        try {
            vehicleToCompare = collectionManager.createVehicle(false);
        }
        catch (IncorrectCreationException exception){
            System.out.println("ERROR: Unknown error during creation process");
            return;
        }
        for (String string : collectionManager.getVehicleMap().keySet()){
            if (vehicleToCompare.compareTo(collectionManager.getVehicleByKey(string)) > 0){
                keysToRemove.add(string);
            }
        }
        for (String key: keysToRemove){
            System.out.printf("Vehicle %s successfully removed\n", collectionManager.getVehicleByKey(key).getName());
            collectionManager.remove(key);
        }
    }

    @Override
    public void executeInScriptMode(String[] args, List<String> text){
        verifyArguments(args);
        Vehicle newVehicle;
        try {
            newVehicle = new Vehicle(collectionManager.updateId(), text.get(0), new Coordinates(Float.valueOf(text.get(1).split(" ")[0]), Double.valueOf(text.get(1).split(" ")[1])), LocalDate.now(), Integer.valueOf(text.get(2)), VehicleType.valueOf(text.get(3)), FuelType.valueOf(text.get(4)));
        }
        catch (Exception exception){
            System.out.println("ERROR: Not a valid object created");
            return;
        }
        for (String string : collectionManager.getVehicleMap().keySet()){
            if (newVehicle.compareTo(collectionManager.getVehicleByKey(string)) > 0){
                collectionManager.remove(string);
            }
        }
    }
    public void verifyArguments(String[] args){
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
    }
}
