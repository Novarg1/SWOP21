package car;

public enum CarGearbox {
	MANUAL,
	AUTOMATIC;
	
	@Override
	public String toString() {
		switch(this) {
		case MANUAL: return "6 speed manual";
		case AUTOMATIC: return "5 speed automatic";
		default: throw new IllegalArgumentException();
		}
	}
}
