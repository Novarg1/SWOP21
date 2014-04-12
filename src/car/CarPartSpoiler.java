package car;

import java.util.LinkedList;

public enum CarPartSpoiler implements CarPart
{

	HIGH,
	LOW;
	
	@Override
	public String toString() {
		switch(this) {
		case HIGH: return "high";
		case LOW:return "low";
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
	
	public static LinkedList<CarPartSpoiler> options()
	{
		LinkedList<CarPartSpoiler> toret = new LinkedList<CarPartSpoiler>();
		for(CarPartSpoiler spoiler : CarPartSpoiler.values())
		{
			toret.add(spoiler);
		}
		return toret;
	}
	
	private boolean installed = false;
}
