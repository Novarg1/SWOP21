package view;

import java.util.ArrayList;
import java.util.List;

import util.LineReader;
import vehicle.order.OrderBuilder;
import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Part;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Spoiler;
import vehicle.parts.Wheels;
import controllers.OrderController;
import controllers.SystemController;

/**
 * Another level of abstraction
 * This view is intended to be the parent of all views that allow the
 * user to place orders and has functionality to assist with the 
 * building of correct order specifications
 * @author jonathanlangens
 *
 */
public abstract class ViewOrderForm extends View
{
	protected OrderController orderController;
	
	protected ViewOrderForm(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
		
		orderController = new OrderController(systemController);
	}
	
	/**
	 * Provide the user with a UI to select the passed option of type on
	 * the specification. If the option is not mandatory this field should be set
	 * to false. In that case the function first asks whether or not this
	 * option should be presented. If there are no viable options to choose or
	 * the part is mandatory and there is only 1 option then that option gets
	 * automatically chosen.
	 * @param spec
	 * @param type
	 * @param mandatory
	 * @throws Exception
	 */
	protected void setOption(OrderBuilder spec, Class<? extends Part> type, boolean mandatory) throws Exception
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
				List<Part> l = new ArrayList<>(spec.getViableOptions(type));
				System.out.println("Added " + l.get(0));
				spec.add(l.get(0));
			}
		}
		while(!spec.containsPart(type))
		{
			System.out.println("What " + type.toString() + " would you like?");
			List<Part> options = new ArrayList<>(spec.getViableOptions(type));
			
			int index = 1;
			for(Part p : options)
				System.out.println("(" + (index++) + ") " + p);
			
			int choice = LineReader.readInt();
			
			if(choice > 0 && choice <= options.size())
				spec.add(options.get(choice - 1));
		}
	}

	/**
	 * Sets the body option on a specification
	 * @param spec
	 */
	protected void setBody(OrderBuilder spec) {
		try {
			this.setOption(spec, Body.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Sets the color option on a specification
	 * @param spec
	 */
	protected void setColor(OrderBuilder spec) {
		try {
			this.setOption(spec, Color.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Sets the engine option on a specification
	 * @param spec
	 */
	protected void setEngine(OrderBuilder spec) {
		try {
			this.setOption(spec, Engine.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Sets the gearbox option on a specification
	 * @param spec
	 */
	protected void setGearbox(OrderBuilder spec) {
		try {
			this.setOption(spec, Gearbox.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Sets the seats option on a specification
	 * @param spec
	 */
	protected void setSeats(OrderBuilder spec) {
		try {
			this.setOption(spec, Seats.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Sets the airco option on a specification
	 * @param spec
	 */
	protected void setAirco(OrderBuilder spec) {
		try {
			this.setOption(spec, Airco.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Sets the wheels option on a specification
	 * @param spec
	 */
	protected void setWheels(OrderBuilder spec) {
		try {
			this.setOption(spec, Wheels.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Sets the spoiler option on a specification
	 * @param spec
	 */
	protected void setSpoiler(OrderBuilder spec) {
		try {
			this.setOption(spec, Spoiler.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
