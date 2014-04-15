package car;

import java.util.LinkedList;

public enum Spoiler implements CarPart
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
	
	public static LinkedList<Spoiler> options()
	{
		LinkedList<Spoiler> toret = new LinkedList<Spoiler>();
		for(Spoiler spoiler : Spoiler.values())
		{
			toret.add(spoiler);
		}
		return toret;
	}
	
	private boolean installed = false;
	private int time = 0;

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "bolt the spoiler on the car";
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return time;
	}
}
