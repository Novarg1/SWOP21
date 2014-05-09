package vehicle.parts;

public enum Color implements Carpart {

	RED, BLUE, BLACK, WHITE, GREEN, YELLOW;

	private static final int workStationID = 0;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	@Override
	public String getAssemblyInstructions() {
		return "paint the car " +this+".";
	}
}
