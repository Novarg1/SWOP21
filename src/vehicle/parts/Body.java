package vehicle.parts;

public enum Body implements Carpart {

	SEDAN, BREAK, SPORT, PLATFORM, CLOSED;
	
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