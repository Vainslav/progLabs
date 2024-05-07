package org.example.network;


import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private String vehicle=null;

    public Request(String command){
        this.command = command;
    }
    public Request(String command, String vehicle) {
        this.command = command;
        this.vehicle = vehicle;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public static Request getInitRequest(){
        return new Request("init");
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "{ command: " + command + ", vehicle: " + vehicle + " }";
    }
}
