package tests;

import org.junit.Test;

import car.ModelASpec;
import car.OrderSpecification;
import car.parts.Airco;
import car.parts.Body;
import car.parts.Carpart;
import car.parts.Color;
import car.parts.Engine;
import car.parts.Gearbox;
import car.parts.Seats;
import car.parts.Spoiler;
import car.parts.Wheels;

public class TestCarSpecification 
{
	@Test
	public void TestSpecificationA()
	{
		OrderSpecification spec = new ModelASpec();
		
		assert(spec.getViableOptions(Body.class).size() == 2);
		assert(spec.getViableOptions(Color.class).size() == 4);
		assert(spec.getViableOptions(Engine.class).size() == 2);
		assert(spec.getViableOptions(Gearbox.class).size() == 3);
		assert(spec.getViableOptions(Airco.class).size() == 2);
		assert(spec.getViableOptions(Seats.class).size() == 3);
		assert(spec.getViableOptions(Wheels.class).size() == 3);
		assert(spec.getViableOptions(Spoiler.class).size() == 0);
		
		assert(!spec.containsPart(Body.class));
		assert(!spec.containsPart(Color.class));
		assert(!spec.containsPart(Engine.class));
		assert(!spec.containsPart(Gearbox.class));
		assert(!spec.containsPart(Airco.class));
		assert(!spec.containsPart(Seats.class));
		assert(!spec.containsPart(Wheels.class));
		assert(!spec.containsPart(Spoiler.class));
		
		spec.add((Carpart) spec.getViableOptions(Body.class).toArray()[0]);
		assert(!spec.isValid());

		spec.add((Carpart) spec.getViableOptions(Color.class).toArray()[0]);
		assert(!spec.isValid());
		
		spec.add(Engine.PERFORMANCE);
		assert(!spec.isValid());

		spec.add((Carpart) spec.getViableOptions(Gearbox.class).toArray()[0]);
		assert(!spec.isValid());
		
		spec.add((Carpart) spec.getViableOptions(Airco.class).toArray()[0]);
		assert(!spec.isValid());

		spec.add((Carpart) spec.getViableOptions(Seats.class).toArray()[0]);
		assert(!spec.isValid());

		spec.add((Carpart) spec.getViableOptions(Wheels.class).toArray()[0]);
		
		assert(spec.isValid());
		
		assert(spec.containsPart(Body.class));
		assert(spec.containsPart(Color.class));
		assert(spec.containsPart(Engine.class));
		assert(spec.containsPart(Gearbox.class));
		assert(spec.containsPart(Airco.class));
		assert(spec.containsPart(Seats.class));
		assert(spec.containsPart(Wheels.class));
		assert(!spec.containsPart(Spoiler.class));
	}
}
