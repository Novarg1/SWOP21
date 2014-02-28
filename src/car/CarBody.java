package car;

public enum CarBody {
	BODY_SEDAN,
	BODY_BREAK;
	
	@Override
	public String toString() {
		switch(this) {
		case BODY_SEDAN: return "sedan";
		case BODY_BREAK: return "break";
		default: throw new IllegalArgumentException();
		}
	}
}