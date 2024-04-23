package lab.managers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lab.collection.Vehicle;
import lab.exceptions.CollectionNotValidException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DumpManager {
    private CollectionManager collectionManager;
    private File file;

    public DumpManager(CollectionManager collectionManager){
        this.collectionManager = collectionManager;

    }
    public void setFile(String fileName){
        File fileLocal = new File(fileName+".xml");
        if (fileLocal.exists()) {
            if (fileLocal.canRead() & fileLocal.canWrite())
                file = new File(fileName+".xml");
            else{
                System.out.println("ERROR: File lacks access modifiers");
                System.exit(0);
            }
        }
        else{
            System.out.println("ERROR: File not found");
            System.exit(0);
        }
    }

    public void load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule timeModule = new JavaTimeModule();
        XmlMapper xmlMapper = new XmlMapper();
        mapper.registerModule(timeModule);
        xmlMapper.registerModule(timeModule);
        HashMap<String,Vehicle> vehicles = new LinkedHashMap<>();
        try {
            vehicles = xmlMapper.readValue(file, HashMap.class);
        }
        catch (InvalidFormatException exception){
            System.out.println("ERROR DURING FILE READING: Incorrect value type\n\n");
        }
        catch (MismatchedInputException | JsonParseException exception){
            System.out.println("ERROR DURING FILE READING: Incorrect structure\n\n");
        }
        catch (JsonMappingException exception){
            System.out.println("ERROR DURING FILE READING: Value out of range\n\n");
        }
        int i = 0;
        for (String key : vehicles.keySet()) {
            if (vehicles.get(key) instanceof List) {
                System.out.println("ERROR: Repeating keys");
            }
            else{
                Vehicle vehicle = mapper.convertValue(vehicles.get(key),Vehicle.class);
                try{
                    collectionManager.add(key.split("Vehicle-")[1].strip(),vehicle);
                }
                catch (ArrayIndexOutOfBoundsException exception){
                    collectionManager.add(String.valueOf(i),vehicle);
                    i++;
                }
            }
        }
        if (!collectionManager.isValid()) {
            collectionManager.clear();
            System.out.println("ERROR DURING COLLECTION ASSIGNING: Collection is not valid\n\n");
        }
        collectionManager.sort();
    }

    public void dump() throws IOException {
        if (!collectionManager.isValid()){
            throw new CollectionNotValidException();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write("<Vehicles>\n".getBytes(StandardCharsets.UTF_8));
        for (Map.Entry<String,Vehicle> entry: collectionManager.getVehicleMap().entrySet()){
            String data = String.format(
                    String.format("    <Vehicle-%s>%n" +
                            "        <id>%d</id>%n" +
                            "        <name>%s</name>%n" +
                            "        <coordinates>%n" +
                            "            <x>%s</x>%n" +
                            "            <y>%s</y>%n" +
                            "        </coordinates>%n" +
                            "        <creationDate>%s</creationDate>%n" +
                            "        <enginePower>%d</enginePower>%n" +
                            "        <type>%s</type>%n" +
                            "        <fuelType>%s</fuelType>%n" +
                            "    </Vehicle-%s>%n",entry.getKey(),entry.getValue().getId(),entry.getValue().getName(),entry.getValue().getX(),entry.getValue().getY(),entry.getValue().getCreationDate(),entry.getValue().getEnginePower(),entry.getValue().getType(),entry.getValue().getFuelType(),entry.getKey())
            );
            fileOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
        }
        fileOutputStream.write("</Vehicles>".getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
    }
}
