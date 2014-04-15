package car;

import java.util.LinkedList;

public enum Seats implements CarPart
{
	LEATHER_BLACK,
	LEATHER_WHITE,
	VINYL_GREY;
	
	@Override
	public String toString() {
		switch(this) {
		case LEATHER_BLACK: return "black leather";
		case LEATHER_WHITE: return "white leather";
		case VINYL_GREY: return "grey vinyl";
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
	
	public static LinkedList<Seats> options()
	{
		LinkedList<Seats> toret = new LinkedList<Seats>();
		for(Seats seat : Seats.values())
		{
			toret.add(seat);
		}
		return toret;
	}
	
	private boolean installed = false;
	private int time = 0;

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "pick up the seats and insert them in the car";
	}

	@Override
	public int getTimeTaken() {
		// TODO Auto-generated method stub
		return time;
	}
}
