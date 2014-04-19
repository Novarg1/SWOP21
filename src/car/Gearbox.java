package car;

public enum Gearbox implements CarPart {

	MANUAL5, MANUAL6, AUTOMATIC;

	private static final int workStationID = 1;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	public String getAssemblyInstructions() {
		return "install "+this+" gearbox";
	}
}
