package moves;

import ru.ifmo.se.pokemon.*;

public class CalmMind extends StatusMove{
	public CalmMind () {
		super(Type.PSYCHIC,0,100000);
	}

	@Override
	protected void applySelfEffects(Pokemon p){
		p.setMod(Stat.SPECIAL_ATTACK,1);
		p.setMod(Stat.SPECIAL_DEFENSE,1);
	}
	
	@Override
	protected String describe(){
		return "is using Calm Mind";
	}
}