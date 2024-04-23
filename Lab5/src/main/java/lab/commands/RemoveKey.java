package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.managers.CollectionManager;

public class RemoveKey extends Command{
    private CollectionManager collectionManager;

    public RemoveKey(CollectionManager collectionManager){
        super("remove_key null","удалить элемент из коллекции по его ключу");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length>2){
            throw new ExcessiveArgumentsException();
        }
        if (args.length==1){
            throw new NotEnoughArgumentsException();
        }
        if (collectionManager.checkKey(args[1])){
            collectionManager.remove(args[1]);
        }
    }
}
