package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Rotom extends Pokemon{
	public Rotom(String name, int level){
		super(name,level);
		setStats(50,50,77,95,77,91);
		setType(Type.ELECTRIC, Type.GHOST);
		setMove(new ChargeBeam(), new ThunderWave(), new Thunder(), new DarkPulse());
	}
}