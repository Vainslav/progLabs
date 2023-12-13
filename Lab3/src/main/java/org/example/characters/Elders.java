package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.interfaces.Plurable;

public class Elders extends Characters implements Plurable{
    private String pluralName = "Cтарцы";
    private String dativeName = "Cтарцам";
    private String genetiveName = "Cтарцев";
    private String singularName = "Старец";
    public Elders(String name){
        super(name);
    }

    public String enstablish(String text){
        return "наладили"+text;
    }
    public String invite(String text){
        return "пригласили"+text;
    }

    @Override
    public String getPluralName() {
        return this.pluralName;
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
    public String getSingularName() {
        return this.singularName;
    }

    @Override
    public String toString() {
        return "Elders{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elders that = (Elders) o;
        return that.getName() == getName();
    }
}
