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
	private int time = 0;

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "place the wheels on the car";
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return time;
	}
}
