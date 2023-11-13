package moves;

import ru.ifmo.se.pokemon.*;

public class Growl extends StatusMove{
	public Growl () {
		super(Type.NORMAL,0,100);
	}
	
	protected void applyOppEfects(Pokemon p){
		p.setMod(Stat.ATTACK, -1);
	}
	
	@Override
	protected String describe(){
		return "is using Growl";
	}
}