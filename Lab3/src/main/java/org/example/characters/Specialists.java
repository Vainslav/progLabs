package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.interfaces.Plurable;

public class Specialists extends Characters implements Plurable{
    private String pluralName = "специалисты";
    private String dativeName = "специалистам";
    private String genetiveName = "специалистов";
    private String singularName = "специалист";

    public Specialists(String name){
        super(name);
    }

    public String took(String text){
        return "захватили с собой "+ text;
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
        return "Specialists{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
