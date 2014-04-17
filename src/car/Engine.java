package car;

public enum Engine implements CarPart {

	STANDARD, PERFORMANCE, ULTRA;

	@Override
	public void install(Car car) {
		car.setEngine(this);
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getEngine());
	}
	
	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" engine";
	}
}
