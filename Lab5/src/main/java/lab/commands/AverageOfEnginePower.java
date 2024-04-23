package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.collection.Vehicle;
import lab.managers.CollectionManager;

import java.io.IOException;

public class AverageOfEnginePower extends Command{

    private CollectionManager collectionManager;

    public AverageOfEnginePower(CollectionManager collectionManager){
        super("average_of_engine_power","вывести среднее значение поля enginePower для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        int combinedEnginePower = 0;
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        for (Vehicle vehicle : collectionManager.getVehicleMap().values()){
            combinedEnginePower = combinedEnginePower + vehicle.getEnginePower();
        }
        System.out.println("Average engine power of all Vehicles:"+combinedEnginePower/collectionManager.getSize());
    }
}
