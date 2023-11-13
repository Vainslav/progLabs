package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Ariados extends Spinarak{
	public Ariados(String name, int level){
		super(name,level);
		setStats(70,90,70,60,70,40);
		addMove(new SmartStrike());
	}
}