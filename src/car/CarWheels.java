package car;

public enum CarWheels {
	COMFORT,
	SPORTS;
	
	@Override
	public String toString() {
		switch(this) {
		case COMFORT: return "comfort";
		case SPORTS: return "sports";
		default: throw new IllegalArgumentException();
		}
	}
}
