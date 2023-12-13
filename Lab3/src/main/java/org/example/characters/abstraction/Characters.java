package org.example.characters.abstraction;

import org.example.interfaces.HasCases;
import org.example.interfaces.Plurable;

public abstract class Characters implements HasCases{
    private String name;
    public Characters(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return that.getName() == getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
