package car;

public enum Airco implements CarPart {

	MANUAL, AUTOMATIC;

	@Override
	public void install(Car car) {
		car.setAirco(this);
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getAirco());
	}
	
	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" airco";
	}
}
