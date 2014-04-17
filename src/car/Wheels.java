package car;

public enum Wheels implements CarPart {

	WINTER, COMFORT, SPORTS;

	@Override
	public void install(Car car) {
		car.setWheels(this);
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getWheels());
	}

	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" wheels";
	}
}
