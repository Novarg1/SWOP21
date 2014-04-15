package car;

import java.util.LinkedList;

public enum Color implements CarPart
{
	RED,
	BLUE,
	BLACK,
	WHITE,
	GREEN,
	YELLOW;
	
	@Override
	public String toString() {
		switch(this) {
		case RED: return "red";
		case BLUE: return "blue";
		case BLACK: return "black";
		case WHITE: return "white";
		case GREEN: return "green";
		case YELLOW: return "yellow";
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
	
	public static LinkedList<Color> options()
	{
		LinkedList<Color> toret = new LinkedList<Color>();
		for(Color color : Color.values())
		{
			toret.add(color);
		}
		return toret;
	}
	
	private boolean installed = false;
	private int time = 0;

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "paint the car " + this;
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return time;
	}
}
