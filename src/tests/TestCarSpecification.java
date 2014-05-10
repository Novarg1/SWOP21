package tests;

import org.junit.Test;

import vehicle.order.ModelABuilder;
import vehicle.order.OrderBuilder;
import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Carpart;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Spoiler;
import vehicle.parts.Wheels;

public class TestCarSpecification 
{
	@Test
	public void TestSpecificationA()
	{
		OrderBuilder spec = new ModelABuilder();
		
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
		
		spec.add(Engine.PERFORMANCE_25DL_V6);
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
