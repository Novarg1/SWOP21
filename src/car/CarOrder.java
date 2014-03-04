package car;

public class CarOrder {

	private CarModelSpecification specification;

	public CarOrder(CarModelSpecification specification)
	{
		this.specification = specification;
	}

	@Override
	public String toString() {
		return this.specification.toString();
	}

}
