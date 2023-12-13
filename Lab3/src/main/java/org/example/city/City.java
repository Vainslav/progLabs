package org.example.city;

import org.example.city.enums.Architecture;
import org.example.city.enums.Mastery;
import org.example.interfaces.HasCases;
import org.example.interfaces.Plurable;

public class City implements Plurable, HasCases {
    private String pluralName = "города";
    private String dativeName = "городу";
    private String genetiveName = "города";
    private String singularName = "город";

    private String name;
    private Architecture style;
    private Mastery mastery;

    public City(String name, Architecture style, Mastery mastery){
        this.name = name;
        this.style = style;
        this.mastery = mastery;
    }

    public String compareStyle(City city){
        if (getStyle()==city.getStyle()){
            return "Архитектурой напоминавший";
        }
        return "Архитектурно непохож";
    }

    public String compareMastery(City city){
        if (getMastery()==city.getMastery()){
            return "сравним по мастерству";
        }
        return "мастерством исполнения превзошедший";
    }
    public Architecture getStyle() {
        return style;
    }

    public void setStyle(Architecture style) {
        this.style = style;
    }

    public Mastery getMastery() {
        return mastery;
    }

    public void setMastery(Mastery mastery) {
        this.mastery = mastery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                "style='" + style + '\'' +
                "mastery=" + mastery + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return city.getName()==getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
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
    public String getPluralName() {
        return this.pluralName;
    }

    @Override
    public String getSingularName() {
        return this.singularName;
    }
}
