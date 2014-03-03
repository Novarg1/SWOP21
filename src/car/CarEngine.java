package car;

public enum CarEngine implements CarPart {
	STANDARD,
	PERFORMANCE;
	
	@Override
	public String toString() {
		switch(this) {
		case STANDARD: return "2.0l 4 cylinders";
		case PERFORMANCE: return "2.5l V6";
		default: throw new IllegalArgumentException();
		}
	}
}
