package car;

public enum CarColor implements CarPart {
	RED,
	BLUE,
	BLACK,
	WHITE;
	
	@Override
	public String toString() {
		switch(this) {
		case RED: return "red";
		case BLUE: return "blue";
		case BLACK: return "black";
		case WHITE: return "white";
		default: throw new IllegalArgumentException();
		}
	}
}
