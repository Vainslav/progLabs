package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.interfaces.Plurable;

public class Phosphorescents extends Characters implements Plurable{
    private String pluralName = "фосфоресцирующие организмы";
    private String dativeName = "фосфоресцирующим организмам";
    private String genetiveName = "фосфоресцирующих организмов";
    private String singularName = "фосфоресцирующий организм";
    private boolean lighting = false;
    public Phosphorescents(String name){
        super(name);
    }

    public void light(){
        this.lighting = this.lighting ? false : true;
    }
    public String getLighting(){
        return getPluralName() + (this.lighting ? "полностью обеспечивали старцев светом" : "не обеспечивали старцев светом");
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
        return "Characters{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phosphorescents that = (Phosphorescents) o;
        return that.getName() == getName();
    }
}
