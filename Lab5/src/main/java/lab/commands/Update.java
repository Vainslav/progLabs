package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.exceptions.IncorrectCreationException;
import lab.exceptions.NotEnoughArgumentsException;
import lab.managers.CollectionManager;


public class Update extends Command{
    private CollectionManager collectionManager;
    public Update(CollectionManager collectionManager){
        super("update id","обновить значение элемента коллекции, id которого равен заданному");
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
        try {
            if (collectionManager.checkKey(args[1])){
                collectionManager.update(args[1]);
                System.out.println("Vehicle successfully updated");
            }
        }
        catch (IncorrectCreationException exception){
            System.out.println("ERROR: Unknown error during creation process");
        }
    }
}
