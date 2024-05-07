package org.example.commands;

import org.example.collection.Vehicle;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Response;

public class Info extends Command{
    private final CollectionManager collectionManager;
    public Info(CollectionManager collectionManager){
        super("info","вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String[] args, Vehicle vehicle) {
        if (args.length>1){
            throw new ExcessiveArgumentsException();
        }
        return new Response(String.format("Тип: %s; Дата инициализации: %s; Количество элементов: %d", "LinkedHashMap",collectionManager.getInitTime(),collectionManager.getSize()));
    }
}
