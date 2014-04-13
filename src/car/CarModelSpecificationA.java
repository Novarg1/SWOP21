package car;

import java.util.LinkedList;

public class CarModelSpecificationA extends CarModelSpecification
{
	public CarModelSpecificationA()
	{
		this.addModelSpecificationRestriction(new CarModelSpecificationRestricitionSport(this));
	}
	
	protected LinkedList<CarPartBody> bodyOptions() {
		LinkedList<CarPartBody> toret = new LinkedList<CarPartBody>();
		toret.add(CarPartBody.BODY_BREAK);
		toret.add(CarPartBody.BODY_SEDAN);
		return toret;
	}
	
	protected LinkedList<CarPartColor> colorOptions()
	{
		LinkedList<CarPartColor> toret = new LinkedList<CarPartColor>();
		toret.add(CarPartColor.BLACK);
		toret.add(CarPartColor.RED);
		toret.add(CarPartColor.BLUE);
		toret.add(CarPartColor.WHITE);
		return toret;
	}
	
	protected LinkedList<CarPartEngine> engineOptions()
	{
		LinkedList<CarPartEngine> toret = new LinkedList<CarPartEngine>();
		toret.add(CarPartEngine.STANDARD);
		toret.add(CarPartEngine.PERFORMANCE);
		return toret;
	}
	
	protected LinkedList<CarPartSpoiler> spoilerOptions()
	{
		return new LinkedList<CarPartSpoiler>();
	}

	@Override
	public CarModelSpecification clone() {
		CarModelSpecificationA toret = new CarModelSpecificationA();
		toret.setBody(body);
		toret.setAirco(airco);
		return toret;
	}
	
	public int getBuildingTimePerWorkstation()
	{
		return 50;
	}

}
