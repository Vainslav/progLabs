package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.interfaces.Plurable;

public class Builders extends Characters implements Plurable{
    private String pluralName = "строители";
    private String dativeName = "строителям";
    private String genetiveName = "строителей";
    private String singularName = "строитель";
    public Builders(String name){
        super(name);
    }

    public String useNewTechs(String where){
        return "использовать" + where + "новейшие технологии";
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
        return "Builders{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Builders that = (Builders) o;
        return that.getName() == getName();
    }
}
