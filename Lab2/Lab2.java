import pokemons.*;
import ru.ifmo.se.pokemon.Battle;


public class Lab2{
	public static void main(String[] args){
		Battle b = new Battle();
		Rotom p1 = new Rotom("Your mom", 5);
		Spinarak p2 = new Spinarak("Your grandmother", 5);
		Ariados p3 = new Ariados("Your sister", 5);
		Kirlia p4 = new Kirlia("Your brother", 5);
		Ralts p5 = new Ralts("Your grandgrandmother", 5);
		Gardevoir p6 = new Gardevoir("Your dad", 5);
		b.addAlly(p1);
		b.addAlly(p2);
		b.addAlly(p3);
		b.addFoe(p4);
		b.addFoe(p5);
		b.addFoe(p6);
		b.go();
	}
}