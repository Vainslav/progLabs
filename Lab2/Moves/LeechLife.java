package moves;

import ru.ifmo.se.pokemon.*;

public class LeechLife extends PhysicalMove{
	public LeechLife () {
		super(Type.BUG,80,100);
	}

	@Override
	protected void applySelfEffects(Pokemon p){
		p.setMod(Stat.HP, -9);
	}
	
	@Override
	protected String describe(){
		return "is using Leech Life";
	}
}