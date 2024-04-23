package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.managers.CollectionManager;

public class Clear extends Command{
    private CollectionManager collectionManager;
    public Clear(CollectionManager collectionManager){
        super("clear","очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        collectionManager.clear();
    }
}
