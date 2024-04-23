package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.managers.CollectionManager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RemoveLowerKey extends Command{
    private CollectionManager collectionManager;

    public RemoveLowerKey(CollectionManager collectionManager){
        super("remove_lower_key null","удалить из коллекции все элементы, ключ которых меньше, чем заданный");
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
        Set<String> keysToRemove = new HashSet<>();
        for (String key : collectionManager.getVehicleMap().keySet()){
            if (args[1].compareTo(key)>0){
                keysToRemove.add(key);
            }
        }
        for (String key: keysToRemove){
            collectionManager.remove(key);
        }
    }
}
