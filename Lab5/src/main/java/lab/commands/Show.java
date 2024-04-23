package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.collection.Vehicle;
import lab.managers.CollectionManager;

public class Show extends Command{
    private CollectionManager collectionManager;
    public Show(CollectionManager collectionManager){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        for (String key: collectionManager.getVehicleMap().keySet()) {
            Vehicle vehicle = collectionManager.getVehicleByKey(key);
            System.out.printf("%s: %n" +
                    "  id: %s%n" +
                    "  name: %s%n" +
                    "  coordinates:%n" +
                    "    x: %s%n" +
                    "    y: %s%n" +
                    "  creationDate: %s%n" +
                    "  type: %s%n" +
                    "  fuelType: %s%n", key, vehicle.getId(), vehicle.getName(), vehicle.getX(), vehicle.getY(), vehicle.getCreationDate(), vehicle.getType(), vehicle.getFuelType());
        }
    }
}
