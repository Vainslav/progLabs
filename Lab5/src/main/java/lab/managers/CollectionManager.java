package lab.managers;

import lab.collection.Coordinates;
import lab.collection.FuelType;
import lab.collection.Vehicle;
import lab.collection.VehicleType;
import lab.exceptions.IncorrectCreationException;

import java.time.LocalDate;
import java.util.*;

public class CollectionManager {
    private Integer id=0;
    private LinkedHashMap<String, Vehicle> vehicleMap = new LinkedHashMap<String,Vehicle>();
    private final LocalDate initTime = LocalDate.now();
    public void add(String key, Vehicle vehicle){
        vehicleMap.put(key,vehicle);
    }

    public LocalDate getInitTime() {
        return initTime;
    }

    public void remove(String key){
        vehicleMap.remove(key);
    }

    public void setVehicleMap(LinkedHashMap<String, Vehicle> vehicleMap) {
        this.vehicleMap = vehicleMap;
    }

    public LinkedHashMap<String, Vehicle> getVehicleMap() {
        return vehicleMap;
    }

    public boolean checkKey(String key){
        return vehicleMap.containsKey(key);
    }

    public Integer getSize(){
        return vehicleMap.size();
    }

    public void clear(){
        vehicleMap.clear();
    }

    public Integer updateId(){
        Integer maxId = 0;
        for (Vehicle vehicle: getVehicleMap().values()){
            maxId = Math.max(vehicle.getId(),maxId);
        }
        id = maxId+1;
        return id;
    }

    public Vehicle getVehicleByKey(String key){
        return vehicleMap.get(key);
    }

    public void sort(){
        ArrayList<Map.Entry<String, Vehicle>> entries = new ArrayList<>(vehicleMap.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        LinkedHashMap<String, Vehicle> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Vehicle> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        setVehicleMap(sortedMap);
    }

    public boolean isValid(){
        Set<Integer> idSet = new HashSet<>();
        for (Vehicle vehicle: vehicleMap.values()){
            if (!vehicle.isValid()){
                return false;
            }
            idSet.add(vehicle.getId());
        }
        if (idSet.size()==vehicleMap.size()) {
            return true;
        }
        else{
            return false;
        }
    }

    public void update(String key){
        Scanner userScanner = new Scanner(System.in);
        Vehicle vehicle = getVehicleByKey(key);
        vehicle.setName(Extractor.extractName(userScanner));
        String[] cords = Extractor.extractCoordinates(userScanner);
        vehicle.setCoordinates(new Coordinates(Float.parseFloat(cords[0]),Double.parseDouble(cords[1])));
        vehicle.setEnginePower(Extractor.extractEnginePower(userScanner));
        vehicle.setType(Extractor.extractVehicleType(userScanner));
        vehicle.setFuelType(Extractor.extractFuelType(userScanner));
    }

    public Vehicle createVehicle(boolean message){
        Scanner userScanner = new Scanner(System.in);
        String name = Extractor.extractName(userScanner);
        String[] cords = Extractor.extractCoordinates(userScanner);
        Integer enginePower = Extractor.extractEnginePower(userScanner);
        VehicleType type = Extractor.extractVehicleType(userScanner);
        FuelType fuelType = Extractor.extractFuelType(userScanner);
        Vehicle vehicle = new Vehicle(updateId(),name,new Coordinates(Float.parseFloat(cords[0]),
                Double.parseDouble(cords[1])),LocalDate.now(),enginePower,type,fuelType);
        if (vehicle.isValid()){
            if (message) {
                System.out.println("Your Vehicle object has been successfully created");
            }
            return vehicle;
        }
        else{
            throw new IncorrectCreationException();
        }
    }
    private static class Extractor{
        public static String extractName(Scanner scanner){
            String name = "";
            while (true){
                System.out.println("Enter the name for your Vehicle");
                name = scanner.nextLine();
                if (name!=""){
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
}

