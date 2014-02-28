package car;

public enum CarSeats {
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
}
