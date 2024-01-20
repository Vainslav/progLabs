package org.example.characters.abstraction;

import org.example.exceptions.IsValidNameException;
import org.example.interfaces.HasCases;

public abstract class Characters implements HasCases{
    private String name;
    public Characters(String name) throws IsValidNameException {
        if (name.matches("[a-zA-Z]")){
            this.name = name;
        }
        else{
            throw new IsValidNameException("Name should contaion only lettes (a-Z)");
        }
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
