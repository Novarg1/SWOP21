package car;

public enum Spoiler implements CarPart {

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
