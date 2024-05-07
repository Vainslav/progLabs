package org.example.managers;

import org.example.collection.Coordinates;
import org.example.collection.FuelType;
import org.example.collection.Vehicle;
import org.example.collection.VehicleType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class ExtractionManager {
    public static Vehicle extractFromFile(Scanner scanner){
        try {
            String name = scanner.nextLine();
            String[] coordinates = scanner.nextLine().split(" ");
            int enginePower = Integer.parseInt(scanner.nextLine());
            VehicleType vehicleType = VehicleType.valueOf(scanner.nextLine().toUpperCase());
            FuelType fuelType = FuelType.valueOf(scanner.nextLine().toUpperCase());
            Vehicle vehicle = new Vehicle(1, name, new Coordinates(coordinates), LocalDate.now(), enginePower, vehicleType, fuelType);
            if (vehicle.isValid()) {
                return vehicle;
            }
        }
        catch (Exception e){
            return null;
        }
        return null;
    }
    public static Vehicle createVehicle(Scanner scanner){
        String name = extractName(scanner);
        String[] coordinates = extractCoordinates(scanner);
        int enginePower = extractEnginePower(scanner);
        VehicleType vehicleType = extractVehicleType(scanner);
        FuelType fuelType = extractFuelType(scanner);
        Vehicle vehicle = new Vehicle(1,name,new Coordinates(coordinates), LocalDate.now(),enginePower,vehicleType,fuelType);
        if (vehicle.isValid()){
            return vehicle;
        }
        System.out.println("Error during vehicle creation");
        return null;
    }
    public static String extractName(Scanner scanner){
        String name = "";
        while (true){
            System.out.println("Enter the name for your Vehicle");
            name = scanner.nextLine();
            if (!Objects.equals(name, "")){
                break;
            }
            else{
                System.out.println("Name can't be blank");
            }
        }
        return name;
    }
    public static String[] extractCoordinates(Scanner scanner){
        String[] cords;
        while (true) {
            System.out.println("Enter coordinates for the Vehicle in the following format: x y (x - float, y - integer > -122)");
            cords = scanner.nextLine().split(" ");
            if (cords[0]==""){
                System.out.println("No value for x. Try again");
                continue;
            }
            if (cords.length>2){
                System.out.println("Too many arguments. Try again");
                continue;
            }
            if (cords.length<2){
                System.out.println("Not enough arguments. Try again");
                continue;
            }
            try {
                Float.parseFloat(cords[0]);
            }
            catch (Exception exception){
                System.out.println("Incorrect value of x. Try again");
            }
            try {
                int y = Integer.parseInt(cords[1]);
                if (y>-122){
                    break;
                }
                else{
                    System.out.println("y need to be greater than -122");
                }
            }
            catch (Exception exception){
                System.out.println("Incorrect value of y. Try again");
            }
        }
        return cords;
    }

    public static Integer extractEnginePower(Scanner scanner){
        int enginePower;
        while (true){
            System.out.println("Enter engine power of your Vehicle (it should be integer > 0)");
            try {
                enginePower = Integer.parseInt(scanner.nextLine());
                if (enginePower>0){
                    break;
                }
                else {
                    System.out.println("Engine power not greater than 0. Try again");
                }
            }
            catch (Exception exception){
                System.out.println("Not an integer. Try again");
            }
        }
        return enginePower;
    }

    public static VehicleType extractVehicleType(Scanner scanner){
        VehicleType vehicleType;
        while (true){
            System.out.println("Enter type of your Vehicle (CAR, SUBMARINE, SHIP, CHOPPER, SPACESHIP)");
            try {
                vehicleType = VehicleType.valueOf(scanner.nextLine().toUpperCase());
                break;
            }
            catch (Exception exception){
                System.out.println("Incorrect Vehicle type. Try again");
            }
        }
        return vehicleType;
    }

    public static FuelType extractFuelType(Scanner scanner){
        FuelType fuelType;
        while (true){
            System.out.println("Enter fuel type of your Vehicle (GASOLINE, ALCOHOL, PLASMA)");
            try {
                fuelType = FuelType.valueOf(scanner.nextLine().toUpperCase());
                break;
            }
            catch (Exception exception){
                System.out.println("Incorrect fuel type. Try again");
            }
        }
        return fuelType;
    }
}
