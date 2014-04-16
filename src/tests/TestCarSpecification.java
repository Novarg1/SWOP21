package tests;

import org.junit.Test;

import car.ModelSpecification;
import car.ModelASpec;
import car.Engine;

public class TestCarSpecification 
{
	@Test
	public void TestSpecificationA()
	{
		ModelSpecification spec = new ModelASpec();
		
		assert(spec.getViableBodyOptions().size() == 2);
		assert(spec.getViableColorOptions().size() == 4);
		assert(spec.getViableEngineOptions().size() == 2);
		assert(spec.getViableGearboxOptions().size() == 3);
		assert(spec.getViableAircoOptions().size() == 2);
		assert(spec.getViableSeatsOptions().size() == 3);
		assert(spec.getViableWheelsOptions().size() == 3);
		assert(spec.getViableSpoilerOptions().size() == 0);
		
		assert(!spec.bodyChosen());
		assert(!spec.colorChosen());
		assert(!spec.engineChosen());
		assert(!spec.gearboxChosen());
		assert(!spec.aircoChosen());
		assert(!spec.seatsChosen());
		assert(!spec.wheelsChosen());
		assert(!spec.spoilerChosen());
		
		spec.setBody(spec.getViableBodyOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setColor(spec.getViableColorOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setEngine(Engine.ULTRA);
		assert(!spec.isValid(true));
		
		spec.setEngine(spec.getViableEngineOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setGearbox(spec.getViableGearboxOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setSeats(spec.getViableSeatsOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setAirco(spec.getViableAircoOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setWheels(spec.getViableWheelsOptions().get(0));
		assert(spec.isValid(true));
		
		assert(spec.isValid(false));
		
		assert(spec.bodyChosen());
		assert(spec.colorChosen());
		assert(spec.engineChosen());
		assert(spec.gearboxChosen());
		assert(spec.seatsChosen());
		assert(spec.aircoChosen());
		assert(spec.wheelsChosen());
		assert(!spec.spoilerChosen());
	}
}
