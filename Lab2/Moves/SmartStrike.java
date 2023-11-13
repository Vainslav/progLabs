package moves;

import ru.ifmo.se.pokemon.*;

public class SmartStrike extends PhysicalMove{
	public SmartStrike () {
		super(Type.STEEL,70,10000);
	}
	
	@Override
	protected String describe(){
		return "is using Smart Strike";
	}
}