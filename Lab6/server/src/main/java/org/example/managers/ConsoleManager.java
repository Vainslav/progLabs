package org.example.managers;

import org.example.collection.Coordinates;
import org.example.collection.Vehicle;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleManager extends Thread{
    DumpManager dumpManager;
    CollectionManager collectionManager;
    public ConsoleManager(DumpManager dumpManager, CollectionManager collectionManager){
        this.dumpManager = dumpManager;
        this.collectionManager = collectionManager;
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String[] str = scanner.nextLine().split(" ");
            try {
                str[0].strip();
            }
            catch (Exception e){
                continue;
            }
            if (Objects.equals(str[0], "secretCommand")){
                try {
                    dumpManager.dump();
                } catch (IOException e) {
                    System.out.println("Can't save");
                    continue;
                }
                System.out.println("Saved");
            }
            if (Objects.equals(str[0], "insert")) {
                String name;
                String key;
                try {
                    key = str[1];
                    name = str[2];
                } catch (Exception e) {
                    System.out.println("Where are your arguments???");
                    continue;
                }
                Vehicle vehicle = new Vehicle(collectionManager.updateId(), name);
                if (!collectionManager.checkKey(key)){
                    collectionManager.add(key, vehicle);
                }
                else{
                    System.out.println("Key is present in the collection");
                }
            }
            if (Objects.equals(str[0], "exit")) {
                System.exit(0);
            }
        }
    }
}
