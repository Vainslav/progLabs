package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.interfaces.Plurable;

public class AncientSculptors extends Characters implements Plurable{
    private String pluralName = "Древние скульпторы";
    private String dativeName = "Древним скульпторам";
    private String genetiveName = "Древних скульпторов";
    private String singularName = "Древний скульптор";
    public AncientSculptors(String name){
        super(name);
    }

    public String tell(String text){
        return "рассказывали на своих барельефах"+text;
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
        return "AncientSculptors{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AncientSculptors that = (AncientSculptors) o;
        return that.getName() == getName();
    }
}
