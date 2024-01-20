package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.exceptions.IsValidNameException;
import org.example.interfaces.HasCases;


public class Lake extends Characters{
    private String dativeName = "Лэйку";
    private String genetiveName = "Лэйка";

    public Lake(String name) throws IsValidNameException {
        super(name);
    }

    public String suggest(Boolean right){
        return right ? "правильно предположил" : "неверно предположил";
    }

    @Override
    public String dativeCase() {
        return this.dativeName;
    }

    @Override
    public String genetiveCase() {
        return this.genetiveName;
    }

    @Override
    public String toString() {
        return "Lake{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lake that = (Lake) o;
        return that.getName() == getName();
    }
}
