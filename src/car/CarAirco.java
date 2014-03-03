package car;

public enum CarAirco implements CarPart {
	MANUAL,
	CLIMATE_CONTROL;
	
	@Override
	public String toString() {
		switch(this) {
		case MANUAL: return "manual airco";
		case CLIMATE_CONTROL: return "automatic climate control";
		default: throw new IllegalArgumentException();
		}
	}
}
