package org.example.network;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.collection.Vehicle;
import org.example.commands.HasComplicatedArguments;
import org.example.commands.WorksWithFiles;
import org.example.exceptions.CollectionNotValidException;
import org.example.exceptions.ExcessiveArgumentsException;
import org.example.exceptions.NotEnoughArgumentsException;
import org.example.exceptions.RecursionDepthExceededException;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Objects;

public class Server {
    private final DatagramChannel channel;
    private final int bufferSize = 4096;
    private SocketAddress address;

    public Server(SocketAddress address) throws IOException {
        channel = DatagramChannel.open();
        channel.bind(address);
    }

    public void run() throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        ByteArrayOutputStream bos;
        ByteArrayInputStream bis;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        while (true) {
            bis = new ByteArrayInputStream(listen().getData());
            ois = new ObjectInputStream(bis);
            Request request = (Request) ois.readObject();
            System.out.println(request);
            if (Objects.equals(request.getCommand(), "init")){
                HashMap<String, String> map = new HashMap<>();
                CommandManager.getCommands()
                        .forEach(command -> {
                            if (CommandManager.getCommand(command) instanceof HasComplicatedArguments){
                                map.put(command, "HasComplicatedArguments");
                            } else if (CommandManager.getCommand(command) instanceof WorksWithFiles) {
                                map.put(command, "WorksWithFiles");
                            }
                            else {
                                map.put(command, null);
                            }
                        });
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(map);
                send(bos.toByteArray());
                oos.flush();
                continue;
            }
            try {
                Response response;
                if (request.getVehicle()==null){
                    response = CommandManager.execute((request.getCommand()).split(" "),null);
                }
                else {
                    response = CommandManager.execute((request.getCommand()).split(" "), objectMapper.readValue(request.getVehicle(), Vehicle.class));
                }
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(response);
                send(bos.toByteArray());
                oos.flush();
            }
            catch (ExcessiveArgumentsException e){
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(new Response("ERROR: Excessive arguments"));
                send(bos.toByteArray());
                oos.flush();
            }
            catch (NotEnoughArgumentsException exception){
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(new Response("ERROR: Not enough arguments"));
                send(bos.toByteArray());
                oos.flush();
            }
            catch (CollectionNotValidException exception){
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(new Response("ERROR: Collection cannot be saved because one or more vehicles are not valid"));
                send(bos.toByteArray());
                oos.flush();
            }
            catch (RecursionDepthExceededException exception){
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(new Response("ERROR: Infinite recursion in file"));
                send(bos.toByteArray());
                oos.flush();
            }
        }
    }

    public DatagramPacket listen() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        address = channel.receive(buffer);
        return new DatagramPacket(buffer.array(), buffer.array().length);
    }

    public void send(byte[] bytes) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        channel.send(buffer, address);
    }
}
