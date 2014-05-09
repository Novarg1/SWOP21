package vehicle.parts;

public enum Engine implements Carpart {

	STANDARD_2L_V4, PERFORMANCE_25DL_V6, ULTRA_3L_V8, STANDARD, HYBRID;

	private static final int workStationID = 1;

	@Override
	public int getWorkStationID() {
		return workStationID;
	}

	@Override
	public String getAssemblyInstructions() {
		return "install " + this + " engine";
	}
}
