package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.exceptions.IsValidNameException;
import org.example.interfaces.HasCases;
import org.example.interfaces.Plurable;


public class AncientSculptor extends Characters implements Plurable{
    private String pluralName = "Древние скульпторы";
    private String dativeName = "Древним скульпторам";
    private String genetiveName = "Древних скульпторов";
    private String singularName = "Древний скульптор";
    public AncientSculptor(String name) throws IsValidNameException {
        super(name);
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
        return "AncientSculptor{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AncientSculptor that = (AncientSculptor) o;
        return that.getName() == getName();
    }

    static class Relief{
        public static String tell(String text){
            return "рассказывали на своих барельефах"+text;
        }
    }
}
