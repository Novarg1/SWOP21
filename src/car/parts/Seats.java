package car.parts;

public enum Seats implements Carpart {

	LEATHER_BLACK, LEATHER_WHITE, VINYL_GREY;

	private static final int workStationID = 2;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" seats";
	}
}
