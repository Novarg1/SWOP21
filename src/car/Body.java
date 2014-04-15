package car;

import java.util.LinkedList;

public enum Body implements CarPart
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
	public void install(int time) {
		// TODO Auto-generated method stub
		this.installed = true;
		this.time = time;
		
	}

	@Override
	public boolean isInstalled() {
		// TODO Auto-generated method stub
		return installed;
	}
	
	public static LinkedList<Body> options()
	{
		LinkedList<Body> toret = new LinkedList<Body>();
		for(Body body : Body.values())
		{
			toret.add(body);
		}
		return toret;
	}
	
	private boolean installed = false;
	private int time = 0;

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "install the body";
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return 0;
	}
}