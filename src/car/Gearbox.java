package car;

public enum Gearbox implements CarPart {

	MANUAL5, MANUAL6, AUTOMATIC;

	@Override
	public void install(Car car) {
		car.setGearbox(this);
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getGearbox());
	}

	public String getAssemblyInstructions() {
		return "install "+this+" gearbox";
	}
}
