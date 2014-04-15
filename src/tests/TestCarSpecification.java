package tests;

import org.junit.Test;

import car.Model;
import car.ModelA;
import car.Engine;

public class TestCarSpecification 
{
	@Test
	public void TestSpecificationA()
	{
		Model spec = new ModelA();
		
		assert(spec.getBodyOptions().size() == 2);
		assert(spec.getColorOptions().size() == 4);
		assert(spec.getEngineOptions().size() == 2);
		assert(spec.getGearboxOptions().size() == 3);
		assert(spec.getAircoOptions().size() == 2);
		assert(spec.getSeatsOptions().size() == 3);
		assert(spec.getWheelsOptions().size() == 3);
		assert(spec.getSpoilerOptions().size() == 0);
		
		assert(!spec.getBodyChosen());
		assert(!spec.getColorChosen());
		assert(!spec.getEngineChosen());
		assert(!spec.getGearboxChosen());
		assert(!spec.getAircoChosen());
		assert(!spec.getSeatsChosen());
		assert(!spec.getWheelsChosen());
		assert(!spec.getSpoilerChosen());
		
		spec.setBody(spec.getBodyOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setColor(spec.getColorOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setEngine(Engine.ULTRA);
		assert(!spec.isValid(true));
		
		spec.setEngine(spec.getEngineOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setGearbox(spec.getGearboxOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setSeats(spec.getSeatsOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setAirco(spec.getAircoOptions().get(0));
		assert(spec.isValid(true));
		assert(!spec.isValid(false));
		
		spec.setWheels(spec.getWheelsOptions().get(0));
		assert(spec.isValid(true));
		
		assert(spec.isValid(false));
		
		assert(spec.getBodyChosen());
		assert(spec.getColorChosen());
		assert(spec.getEngineChosen());
		assert(spec.getGearboxChosen());
		assert(spec.getSeatsChosen());
		assert(spec.getAircoChosen());
		assert(spec.getWheelsChosen());
		assert(!spec.getSpoilerChosen());
	}
}
