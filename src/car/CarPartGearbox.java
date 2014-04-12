package car;

import java.util.LinkedList;

public enum CarPartGearbox implements CarPart
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
	public void install() {
		// TODO Auto-generated method stub
		this.installed = true;
	}

	@Override
	public boolean isInstalled() {
		// TODO Auto-generated method stub
		return installed;
	}
	
	public static LinkedList<CarPartGearbox> options()
	{
		LinkedList<CarPartGearbox> toret = new LinkedList<CarPartGearbox>();
		for(CarPartGearbox box : CarPartGearbox.values())
		{
			toret.add(box);
		}
		return toret;
	}
	
	private boolean installed = false;
}
