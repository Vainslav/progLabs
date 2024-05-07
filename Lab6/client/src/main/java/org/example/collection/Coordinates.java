package org.example.collection;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Coordinates implements Comparable<Coordinates>, Serializable {
    private Float x;
    private Double y;

    public Coordinates(Float x, Double y){
        this.x = x;
        this.y = y;
    }
    public Coordinates(String[] cords){
        this.x = Float.valueOf(cords[0]);
        this.y = Double.valueOf(cords[1]);
    }
    public Coordinates(){}

    public Float getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getDistance(){
        return Math.sqrt(getX()*getX()+getY()*getY());
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (!Objects.equals(x, that.x)) return false;
        return Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }

    @Override
    public int compareTo(Coordinates o) {
        return getDistance().compareTo(o.getDistance());
    }
}
