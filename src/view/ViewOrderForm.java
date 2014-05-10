package view;

import java.util.ArrayList;
import java.util.List;

import util.LineReader;
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
	
	protected void setOption(OrderBuilder spec, Class<? extends Carpart> type, boolean mandatory) throws Exception
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

	protected void setBody(OrderBuilder spec) {
		try {
			this.setOption(spec, Body.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setColor(OrderBuilder spec) {
		try {
			this.setOption(spec, Color.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setEngine(OrderBuilder spec) {
		try {
			this.setOption(spec, Engine.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setGearbox(OrderBuilder spec) {
		try {
			this.setOption(spec, Gearbox.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setSeats(OrderBuilder spec) {
		try {
			this.setOption(spec, Seats.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setAirco(OrderBuilder spec) {
		try {
			this.setOption(spec, Airco.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setWheels(OrderBuilder spec) {
		try {
			this.setOption(spec, Wheels.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setSpoiler(OrderBuilder spec) {
		try {
			this.setOption(spec, Spoiler.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
