package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.IncorrectCreationException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.collection.Coordinates;
import lab.collection.FuelType;
import lab.collection.Vehicle;
import lab.collection.VehicleType;
import lab.managers.CollectionManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReplaceIfGreater extends Command implements HasComplicatedArguments{
    private CollectionManager collectionManager;
    public ReplaceIfGreater(CollectionManager collectionManager){
        super("replace_if_greater null {element}", "заменить значение по ключу, если новое значение больше старого");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        verifyArguments(args);
        if (!collectionManager.checkKey(args[1])){
            System.out.println("ERROR: Non existing key");
            return;
        }
        Vehicle vehicle;
        try {
            vehicle = collectionManager.createVehicle(false);
        }
        catch (IncorrectCreationException exception){
            System.out.println("ERROR: Unknown error during creation process");
            return;
        }
        if (vehicle.compareTo(collectionManager.getVehicleByKey(args[1]))>0){
            collectionManager.add(args[1],vehicle);
            System.out.println("Vehicle successfully replaced");
            return;
        }
        System.out.println("Your vehicle is smaller and wasn't replaced");


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
        if (!collectionManager.checkKey(args[1])){
            System.out.println("ERROR: Non existing key");
            return;
        }
        if (newVehicle.compareTo(collectionManager.getVehicleByKey(args[1]))>0){
            collectionManager.add(args[1],newVehicle);
        }
    }

    public void verifyArguments(String[] args){
        if (args.length>2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length==1){
            throw new NotEnoughArgumentsException();
        }
    }
}
