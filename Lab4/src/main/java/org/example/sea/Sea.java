package org.example.sea;

import org.example.exceptions.DepthNotFoundException;
import org.example.interfaces.HasCases;
import org.example.interfaces.Plurable;
import org.example.sea.enums.Depth;

public class Sea implements Plurable, HasCases {
    private String dativeName = "морю";
    private String genetiveName = "моря";
    private String pluralName = "морей";
    private String singularName = "море";
    private Depth depth;
    private String name;
    private Integer temperature;

    public Sea(String name, Depth depth, Integer temperature){
        this.depth = depth;
        this.name = name;
        this.temperature = temperature;
    }

    public String assure(){
        if (getDepth()==null) {
            throw new DepthNotFoundException("Depth assigned no or incorrect value");
        }
        else {
            switch (getDepth()) {
                case Low:
                    return "Низкая глубина моря не могла дать никакой гарантии";
                case High:
                    return "Огромная глубина моря давала гарантию, что внутренний жар земли позволит новым поселенцам жить там сколько потребуется";
                default:
                    return null;
            }
        }
    }

    public Depth getDepth() {
        return depth;
    }

    public void setDepth(Depth depth){
        this.depth = depth;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getTemperature(){
        return this.temperature;
    }

    public void setTemperature(Integer temperature){
        this.temperature = temperature;
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

    @Override
    public String toString() {
        return "Sea{" +
                ", depth=" + depth +
                ", name='" + name + '\'' +
                ", temperature=" + temperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sea sea = (Sea) o;
        return depth == sea.getDepth() && name == sea.getName() && temperature == sea.getTemperature();
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + getTemperature().hashCode() + getDepth().hashCode();
    }
}
