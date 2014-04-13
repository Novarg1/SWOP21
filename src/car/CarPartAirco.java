package car;

import java.util.LinkedList;

public enum CarPartAirco implements CarPart
{
	MANUAL,
	CLIMATE_CONTROL;
	
	@Override
	public String toString() {
		switch(this) {
		case MANUAL: return "manual airco";
		case CLIMATE_CONTROL: return "automatic climate control";
		default: throw new IllegalArgumentException();
		}
	}
	
	public boolean isInstalled()
	{
		return installed;
	}
	
	public static LinkedList<CarPartAirco> options()
	{
		LinkedList<CarPartAirco> toret = new LinkedList<CarPartAirco>();
		for(CarPartAirco airco : CarPartAirco.values())
		{
			toret.add(airco);
		}
		return toret;
	}
	
	private boolean installed=false;
	private int time=0;

	@Override
	public void install(int time) {
		// TODO Auto-generated method stub
		this.installed = true;
		this.time = time;
	}

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "install the airco";
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return time;
	}
}
