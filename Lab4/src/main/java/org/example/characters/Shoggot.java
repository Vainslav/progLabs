package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.exceptions.IsValidNameException;
import org.example.interfaces.Plurable;

public class Shoggot extends Characters implements Plurable{
    private String pluralName = "шогготы";
    private String dativeName = "шогготам";
    private String genetiveName = "шогготов";
    private String singularName = "шоггот";
    public Shoggot(String name) throws IsValidNameException {
        super(name);
    }

    public String obey(){
        return "подчинялись";
    }

    public String achieve(String text){
        return "достигли" + text;
    }

    public String mimicVoices(String text){
        return "подражая их голосам, мелодичными, трубными звуками, слышными, если"+text+"на большом расстоянии";
    }

    public String liftAndDragRocks(){
        return "способных, поднимать и перетаскивать камни";
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
        return "Shoggot{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoggot that = (Shoggot) o;
        return that.getName() == getName();
    }
}
