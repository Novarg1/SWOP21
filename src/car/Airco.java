package car;

public enum Airco implements CarPart {

	MANUAL, AUTOMATIC;
	
	private static final int workStationID = 2;

	@Override
	public String getAssemblyInstructions() {
		return "install " + this + " airco";
	}

	@Override
	public int getWorkStationID() {
		return workStationID;
	}
}
