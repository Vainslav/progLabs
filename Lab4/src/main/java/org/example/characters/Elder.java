package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.exceptions.IsValidNameException;
import org.example.interfaces.Plurable;

public class Elder extends Characters implements Plurable{
    private String pluralName = "Cтарцы";
    private String dativeName = "Cтарцам";
    private String genetiveName = "Cтарцев";
    private String singularName = "Старец";

    public Elder(String name) throws IsValidNameException {
        super(name);
    }

    public String enstablish(String text){
        return "наладили"+text;
    }

    public String invite(String text){
        return "пригласили"+text;
    }

    public String build(Boolean onBottom){
        return "выстроили" + (onBottom ? "не на пологих берегах подземного моря, а на его дне" : "на пологих берегах подземного моря");
    }

    public String transport(){
        return "перенесли в подводный город несколько глыб с великолепными образцами древней резьбы";
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
        return "Elder{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elder that = (Elder) o;
        return that.getName() == getName();
    }
}
