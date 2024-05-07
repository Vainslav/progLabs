package org.example.network;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.collection.Vehicle;
import org.example.commands.CommandManager;
import org.example.managers.ExtractionManager;


import java.io.*;
import java.net.*;
import java.util.*;


public class Client {
    private final DatagramSocket ds;
    private final int bufferLength = 4096;
    ObjectMapper objectMapper;

    public Client(int port) throws SocketException {
        ds = new DatagramSocket(port);
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }
    public void connect() throws SocketException {
        ds.connect(new InetSocketAddress("localhost", 2769));
    }
    public void run() throws IOException, ClassNotFoundException {
        run(null);
    }
    public void run(String path) throws IOException, ClassNotFoundException {
        Scanner userScanner = null;
        if (path==null){
             userScanner = new Scanner(System.in);
        }
        else{
            try{
                userScanner = new Scanner(new File(path));
            }
            catch (IOException e){
                System.out.println("ERROR");
            }
        }
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
        objectOutputStream.writeObject(Request.getInitRequest());
        ByteArrayInputStream initResponse =  sendAndListen(byteArrayInputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(initResponse);
        HashMap<String,String> commands = (HashMap<String, String>) objectInputStream.readObject();
        CommandManager.setCommands(commands);
        System.out.println(commands);
        while (true){
            String[] userInput;
            try{
                userInput = userScanner.nextLine().split(" ");
            }
            catch (NoSuchElementException e){
                return;
            }
            try{
                String command = userInput[0];
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("ERROR: Not a command (try help)\n");
                continue;
            }
            if (commands.containsKey(userInput[0])){
                Request request;
                if (Objects.equals(commands.get(userInput[0]), "HasComplicatedArguments") & path==null){
                    Vehicle vehicle = ExtractionManager.createVehicle(userScanner);
                    if (vehicle==null){
                        continue;
                    }
                    request = new Request(String.join(" ",userInput), objectMapper.writeValueAsString(vehicle));
                }
                else if ((Objects.equals(commands.get(userInput[0]), "HasComplicatedArguments")) & (path!=null)){
                    Vehicle vehicle = ExtractionManager.extractFromFile(userScanner);
                    if (vehicle!= null) {
                        request = new Request(String.join(" ", userInput), objectMapper.writeValueAsString(vehicle));
                    }
                    else{
                        System.out.println("Not a valid object");
                    }
                    continue;
                }
                else if (Objects.equals(commands.get(userInput[0]),"WorksWithFiles")) {
                    request = new Request(String.join(" ",userInput), null);
                    run(userInput[1]);
                    continue;
                } else {
                    request = new Request(String.join(" ",userInput), null);
                }
                byteArrayInputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
                objectOutputStream.writeObject(request);
                ByteArrayInputStream bis = sendAndListen(byteArrayInputStream.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bis);
                Response response = (Response) ois.readObject();
                if (Objects.equals(response.getData(), "shut down")){
                    System.out.println("⣿⣿⡿⣫⣾⠏⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢀⣀⣀⣀⣀⠄⠄⠄⠄⠄⠄\n" +
                            "⣿⡇⠱⠉⠁⠄⠄⠄⠄⠄⠄⢀⣀⣤⣶⣶⣿⣿⣿⣿⣿⣿⣿⣦⠄⠄⠄⠄⠄\n" +
                            "⣿⡇⠄⠄⠄⠄⠄⢀⣠⣛⡩⣩⣭⡹⣿⣿⣿⣿⠞⣛⣛⣛⡲⣿⡇⠄⠄⠄⠄\n" +
                            "⣿⡇⠄⠄⠄⡾⣡⣾⣿⣷⣹⣿⣿⡿⣪⡻⠟⣱⣿⣿⣿⣿⣿⣷⡹⠄⠄⠄⠄\n" +
                            "⣿⡇⠄⠄⣼⡇⣿⣻⣿⠟⡛⢿⣿⣾⣿⡇⢰⣍⢻⡿⠛⢿⣿⡭⣿⣷⠄⠄⠄\n" +
                            "⣿⣧⣄⡀⣿⡇⣮⣽⣿⣮⣉⣾⣿⣿⣿⣇⡸⣿⣿⣆⠛⣰⣿⣾⡿⣿⠄⠄⠄\n" +
                            "⣿⣇⡼⣄⣿⣿⡄⠙⢿⣏⣿⣿⡮⠁⣉⣾⣷⡈⠃⢿⣿⣬⡭⠝⣀⣿⠄⠄⠐\n" +
                            "⡆⡇⣹⣿⣿⣿⣿⡿⠓⠛⣉⣉⣉⣉⣙⣛⠓⠾⣟⢿⣿⣿⣿⣿⣿⣿⣿⠇⠄⠙\n" +
                            "⠁⡇⣞⣿⡿⠋⠁⠄⠄⠈⠉⠙⠛⠛⠻⠿⠿⠿⣶⣌⠻⣿⣿⣿⣿⣿⢗⢴⣆⢣\n" +
                            "⠸⣇⡻⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢻⣷⡌⢿⣿⣿⣿⢸⠼⣣⣾\n" +
                            "⣦⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⢀⠄⠄⠄⠄⠄⠄⠄⠄⠙⠛⠈⣿⡫⡼⢠⣾⣿⣿\n" +
                            "⣿⣇⠄⣀⣠⡀⠄⠄⠴⠾⠿⠿⠶⠶⣦⣤⡀⠄⠄⠄⠄⠄⠄⢨⠯⢁⣿⣿⣿⣿\n" +
                            "⣿⣿⣦⢒⠤⣅⡶⣶⣶⣾⣿⣿⣿⣷⣶⣮⣍⠢⠄⠄⠄⠄⠄⠐⢠⣾⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣧⡐⠫⣉⡿⣬⡞⢿⣿⢯⠽⣶⡽⢟⣛⢖⣨⣛⠛⢃⣴⣿⣿⣿⣿⣿⣿");
                    System.exit(0);
                }
                System.out.println(response.getData());
            }
            else{
                System.out.println("ERROR: Not a command (try help)\n");
            }
        }
    }
    public void send(byte[] dataToSend) throws IOException {
        DatagramPacket dp = new DatagramPacket(dataToSend, dataToSend.length, new InetSocketAddress("localhost", 2769));
        ds.send(dp);
    }

    public ByteArrayInputStream listen() throws IOException {
        DatagramPacket receiveDp = new DatagramPacket(new byte[bufferLength],bufferLength);
        ds.receive(receiveDp);
        ByteArrayInputStream inputStream;
        inputStream = new ByteArrayInputStream(receiveDp.getData());
        return inputStream;
    }

    public ByteArrayInputStream sendAndListen(byte[] dataToSend) throws IOException {
        send(dataToSend);
        return listen();
    }
}
