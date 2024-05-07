package org.example.commands;

import org.example.collection.Vehicle;
import org.example.network.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class Command implements Describable{
    private final String name;
    private final String description;

    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public abstract Response execute(String[] args, Vehicle vehicle) throws IOException;

    public String getName() {
        return name;
    }

    public String executeInScriptMode(String[] args, List<String> text)throws IOException{
        return execute(args, null).getData();
    }

    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (!Objects.equals(name, command.name)) return false;
        return (!Objects.equals(description, command.description));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,description);
    }
}
