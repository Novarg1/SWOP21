package car.parts;

public enum Body implements Carpart {

	SEDAN, BREAK, SPORT;
	
	private static final int workStationID = 0;
	
	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" body";
	}
}