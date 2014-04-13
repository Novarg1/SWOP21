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

	@Override
	public void install() {
		// TODO Auto-generated method stub
		this.installed = true;
		
	}
}
