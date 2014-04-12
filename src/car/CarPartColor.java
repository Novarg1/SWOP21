package car;

import java.util.LinkedList;

public enum CarPartColor implements CarPart
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
	public void install() {
		// TODO Auto-generated method stub
		this.installed = true;
	}

	@Override
	public boolean isInstalled() {
		// TODO Auto-generated method stub
		return installed;
	}
	
	public static LinkedList<CarPartColor> options()
	{
		LinkedList<CarPartColor> toret = new LinkedList<CarPartColor>();
		for(CarPartColor color : CarPartColor.values())
		{
			toret.add(color);
		}
		return toret;
	}
	
	private boolean installed = false;
}
