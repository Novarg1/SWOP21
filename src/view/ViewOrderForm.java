package view;

import java.util.ArrayList;
import java.util.List;

import util.LineReader;
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
import controllers.OrderController;
import controllers.SystemController;

public abstract class ViewOrderForm extends View
{
	protected OrderController orderController;
	
	protected ViewOrderForm(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
		
		orderController = new OrderController(systemController);
	}
	
	protected void setOption(OrderSpecification spec, Class<? extends Carpart> type, boolean mandatory) throws Exception
	{
		if(mandatory ==  false)
		{
			if(spec.getViableOptions(type).size() == 0)
			{
				return;
			}
			System.out.println("Would you like to order a " + type.toString() + "?");
			if(LineReader.readLine().toLowerCase().startsWith("n"))
				return;
		}
		else
		{
			if(spec.getViableOptions(type).size() == 0)
			{
				throw new Exception("mandatory part has no options");
			}
			if(spec.getViableOptions(type).size() == 1)
			{
				List<Carpart> l = new ArrayList<>(spec.getViableOptions(type));
				System.out.println("Added " + l.get(0));
				spec.add(l.get(0));
			}
		}
		while(!spec.containsPart(type))
		{
			System.out.println("What " + type.toString() + " would you like?");
			List<Carpart> options = new ArrayList<>(spec.getViableOptions(type));
			
			int index = 1;
			for(Carpart p : options)
				System.out.println("(" + (index++) + ") " + p);
			
			int choice = LineReader.readInt();
			
			if(choice > 0 && choice <= options.size())
				spec.add(options.get(choice - 1));
		}
	}

	protected void setBody(OrderSpecification spec) {
		try {
			this.setOption(spec, Body.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setColor(OrderSpecification spec) {
		try {
			this.setOption(spec, Color.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setEngine(OrderSpecification spec) {
		try {
			this.setOption(spec, Engine.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setGearbox(OrderSpecification spec) {
		try {
			this.setOption(spec, Gearbox.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setSeats(OrderSpecification spec) {
		try {
			this.setOption(spec, Seats.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setAirco(OrderSpecification spec) {
		try {
			this.setOption(spec, Airco.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setWheels(OrderSpecification spec) {
		try {
			this.setOption(spec, Wheels.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setSpoiler(OrderSpecification spec) {
		try {
			this.setOption(spec, Spoiler.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
