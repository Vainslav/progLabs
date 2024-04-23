package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.IncorrectCreationException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.collection.Coordinates;
import lab.collection.FuelType;
import lab.collection.Vehicle;
import lab.collection.VehicleType;
import lab.managers.CollectionManager;

import java.time.LocalDate;
import java.util.List;

public class Insert extends Command implements HasComplicatedArguments{

    private CollectionManager collectionManager;

    public Insert(CollectionManager collectionManager){
        super("insert null {element}", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        verifyArguments(args);
        try{
            if (!collectionManager.checkKey(args[1])) {
                collectionManager.add(args[1], collectionManager.createVehicle(true));
            }
            else{
                System.out.printf("Object with id: %s already exists%n",args[1]);
            }
        }
        catch (IncorrectCreationException exception){
            System.out.println("ERROR: Unknown error during creation process");
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
        if (newVehicle.isValid()) {
            if (!collectionManager.checkKey(args[1])) {
                collectionManager.add(args[1], newVehicle);
            }
            else{
                System.out.printf("ERROR: Object with id: %s already exists%n",args[1]);
            }
        }
        else{
            System.out.println("ERROR: Not a valid object created");
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
