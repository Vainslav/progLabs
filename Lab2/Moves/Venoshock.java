package moves;

import ru.ifmo.se.pokemon.*;

public class Venoshock extends SpecialMove{
	public Venoshock() {
		super(Type.POISON,65,100);
	}
	
	@Override
	protected void applyOppEffects(Pokemon p){
		if (p.getCondition() == Status.POISON){
			p.setMod(Stat.HP,9);
			System.out.print("Venoshock");
		}
	}
	
	@Override
	protected String describe(){
		return "is using Venoshock";
	}
}