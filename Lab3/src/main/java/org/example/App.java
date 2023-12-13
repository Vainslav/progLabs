package org.example;

import org.example.city.enums.Architecture;
import org.example.city.City;
import org.example.city.enums.Mastery;

public class App 
{
    public static void main( String[] args )
    {
        City city = new City("popa", Architecture.БАРОККО, Mastery.MASTERFULL);
    }
}
