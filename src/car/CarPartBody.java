package car;

import java.util.LinkedList;

public enum CarPartBody implements CarPart
{
	BODY_SEDAN,
	BODY_BREAK,
	BODY_SPORT;
	
	@Override
	public String toString() {
		switch(this) {
		case BODY_SEDAN: return "sedan";
		case BODY_BREAK: return "break";
		case BODY_SPORT: return "sport";
		default: throw new IllegalArgumentException();
		}
	}

	@Override
	public void install() {
		// TODO Auto-generated method stub
		this.installed = true;
		
	}

	@Override
	public boolean isInstalled() {
		// TODO Auto-generated method stub
		return installed;
	}
	
	public static LinkedList<CarPartBody> options()
	{
		LinkedList<CarPartBody> toret = new LinkedList<CarPartBody>();
		for(CarPartBody body : CarPartBody.values())
		{
			toret.add(body);
		}
		return toret;
	}
	
	private boolean installed = false;
}