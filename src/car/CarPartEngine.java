package car;

import java.util.LinkedList;

public enum CarPartEngine implements CarPart
{
	STANDARD,
	PERFORMANCE,
	ULTRA;
	
	@Override
	public String toString() {
		switch(this) {
		case STANDARD: return "2.0l 4 cylinders";
		case PERFORMANCE: return "2.5l V6";
		case ULTRA:return "3.0l 8 cylinders";
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
	
	public static LinkedList<CarPartEngine> options()
	{
		LinkedList<CarPartEngine> toret = new LinkedList<CarPartEngine>();
		for(CarPartEngine engine : CarPartEngine.values())
		{
			toret.add(engine);
		}
		return toret;
	}
	
	private boolean installed = false;
	private int time = 0;

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "place the engine in the car";
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return time;
	}
}
