package car.parts;

public enum Spoiler implements Carpart {

	HIGH, LOW;

	private static final int workStationID = 2;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" spoiler.";
	}
}
