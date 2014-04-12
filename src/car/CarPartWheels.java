package car;

import java.util.LinkedList;

public enum CarPartWheels implements CarPart
{
	WINTER,
	COMFORT,
	SPORTS;
	
	@Override
	public String toString() {
		switch(this) {
		case WINTER: return "winter";
		case COMFORT: return "comfort";
		case SPORTS: return "sports";
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
	
	public static LinkedList<CarPartWheels> options()
	{
		LinkedList<CarPartWheels> toret = new LinkedList<CarPartWheels>();
		for(CarPartWheels wheels : CarPartWheels.values())
		{
			toret.add(wheels);
		}
		return toret;
	}
	
	private boolean installed = false;
}
