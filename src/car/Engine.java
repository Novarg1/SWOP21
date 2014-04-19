package car;

public enum Engine implements CarPart {

	STANDARD, PERFORMANCE, ULTRA;

	private static final int workStationID = 1;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}
	
	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" engine";
	}
}
