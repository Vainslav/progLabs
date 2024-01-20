package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.exceptions.IsValidNameException;
import org.example.interfaces.HasCases;
import org.example.interfaces.Plurable;

public class Settler extends Characters implements Plurable, HasCases {
    private String pluralName = "поселенцы";
    private String singularName = "поселенец";
    private String dativeName = "поселенцам";
    private String genetiveName = "поселенцам";

    public Settler(String name) throws IsValidNameException {
        super(name);
    }

    public String adapt(){
        return "без труда приспособились проводить под водой большую часть времени, а позднее и вовсе перестали выходить на берег";
    }

    public String disallowDeathOfFrogs(){
        HasCases frog = new HasCases() {
            @Override
            public String dativeCase() {
                return "жабам";
            }

            @Override
            public String genetiveCase() {
                return "жаб";
            }
        };
        return "они ведь никогда не позволяли"+frog.dativeCase()+"окончательно умереть";
    }

    @Override
    public String dativeCase() {
        return dativeName;
    }

    @Override
    public String genetiveCase() {
        return genetiveName;
    }

    @Override
    public String getPluralName() {
        return pluralName;
    }

    @Override
    public String getSingularName() {
        return singularName;
    }

    @Override
    public String toString() {
        return "Settler{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settler that = (Settler) o;
        return that.getName() == getName();
    }
}
