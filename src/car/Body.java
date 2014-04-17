package car;

public enum Body implements CarPart {

	SEDAN, BREAK, SPORT;
	
	@Override
	public void install(Car car) {
		car.setBody(this);
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getBody());
	}
	
	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" body";
	}
}