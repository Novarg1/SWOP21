package vehicle.parts;

public enum Gearbox implements Carpart {

	MANUAL_5, MANUAL_6, MANUAL_8, AUTOMATIC_5, AUTOMATIC;

	private static final int workStationID = 1;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	public String getAssemblyInstructions() {
		return "install "+this+" gearbox";
	}
}
