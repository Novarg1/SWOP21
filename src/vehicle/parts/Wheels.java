package vehicle.parts;

public enum Wheels implements Carpart {

	WINTER, COMFORT, SPORTS, STANDARD, HEAVY_DUTY;

	private static final int workStationID = 2;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" wheels";
	}
}
