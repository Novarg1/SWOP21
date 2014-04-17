package car;

public enum Spoiler implements CarPart {

	HIGH, LOW;

	@Override
	public void install(Car car) {
		car.setSpoiler(this);;
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getSpoiler());
	}

	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" spoiler.";
	}
}
