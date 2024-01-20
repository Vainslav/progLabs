package org.example.characters;

import org.example.characters.abstraction.Characters;
import org.example.city.City;
import org.example.city.enums.Architecture;
import org.example.city.enums.Mastery;
import org.example.exceptions.IsValidNameException;
import org.example.interfaces.HasCases;
import org.example.interfaces.Plurable;

public class KonstantinTheGreat extends Characters{
    private String dativeName = "Константину Великому";
    private String genetiveName = "Константина Великого";

    public KonstantinTheGreat(String name) throws IsValidNameException {
        super(name);
    }

    public String makePolitics(){
        return "политику Константина Великого";
    }

    public String rob(){
        return "вышеозначенный император в такое же гиблое для исскуства время ограбил Грецию и Азию";
    }
    @Override
    public String dativeCase() {
        return dativeName;
    }

    @Override
    public String genetiveCase() {
        return genetiveName;
    }

    @Override
    public String toString() {
        return "KonstantinTheGreat{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KonstantinTheGreat that = (KonstantinTheGreat) o;
        return that.getName() == getName();
    }

    class Vizantiya extends City {
        public Vizantiya(String name, Architecture style, Mastery mastery) {
            super(name, style, mastery);
        }

        public String makeGreat(){
            return "сделать свою новую столицу, Византию, ещё более прекрасной";
        }

        @Override
        public String toString(){
            return "Vizantiya{" +
                    "name='" + getName() + '\'' +
                    "style='" + getStyle() + '\'' +
                    "mastery=" + getMastery() + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vizantiya city = (Vizantiya) o;
            return city.getName()==getName();
        }
    }
}
