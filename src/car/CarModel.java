package car;

public class CarModel {
	
	private CarModelSpecification  carModelSpecification;
	private String model;
	
	
	public CarModel(String model) {
		this.model = model;
	}
	
	public CarModelSpecification getCarModelSpecification() {
		if (this.carModelSpecification == null) {
			this.carModelSpecification = new CarModelSpecification();
		}
		return this.carModelSpecification;
	}
	
	@Override
	public String toString() {
		return this.model;
	}
	
}
