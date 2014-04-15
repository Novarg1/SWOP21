package car;

import java.util.LinkedList;

public enum Gearbox implements CarPart
{
	MANUAL5,
	MANUAL6,
	AUTOMATIC;
	
	@Override
	public String toString() {
		switch(this) {
		case MANUAL5: return "5 speed manual";
		case MANUAL6: return "6 speed manual";
		case AUTOMATIC: return "5 speed automatic";
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
	
	public static LinkedList<Gearbox> options()
	{
		LinkedList<Gearbox> toret = new LinkedList<Gearbox>();
		for(Gearbox box : Gearbox.values())
		{
			toret.add(box);
		}
		return toret;
	}
	
	private boolean installed = false;
	private int time = 0;

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "install the gearbox";
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return time;
	}
}
