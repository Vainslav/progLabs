package org.example.network;

import java.io.Serializable;

public class Response implements Serializable {
    private String data;
    public Response(){
        this.data = "";
    }
    public Response(String text){
        this.data = text;
    }

    public void add(String string){
        data += string + "\n";
    }

    public String getData() {
        return data;
    }
    public void strip(){
        this.data = data.strip();
    }
}
