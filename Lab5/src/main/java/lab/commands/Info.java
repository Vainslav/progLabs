package lab.commands;

import lab.exceptions.ExcessiveArgumentsException;
import lab.managers.CollectionManager;

public class Info extends Command{
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager){
        super("info","вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        System.out.printf("Тип: %s; Дата инициализации: %s; Количество элементов: %d%n", "LinkedHashMap",collectionManager.getInitTime(),collectionManager.getSize());
    }
}
