package car;

import java.util.LinkedList;

/**
 * A carspecification contains specific parts that a car should have along
 * with all the information of which parts this specific type of model might
 * have and with all the restrictions for this particular type of car.
 * This car also has logic to check whether all restrictions apply, and if the
 * base conditions (which are all parts must be selected except for airco and
 * spoiler and all selected options should be admissible for this type of car)
 * are met.
 * 
 * @author jonathanlangens
 */
public abstract class Model 
{
	protected Body body = null;
	protected Color color = null;
	protected Engine engine = null;
	protected Gearbox gearbox = null;
	protected Seats seats = null;
	protected Airco airco = null;
	protected Wheels wheels = null;
	protected Spoiler spoiler = null;
	protected String type = null;
	
	private Restriction restriction = null;

	public Model()
	{
	}
	
	/**
	 * inspector for this.body
	 * @return this.body
	 */
	public Body getBody()
	{
		return this.body;
	}
	/**
	 * mutator for this.body
	 * @param body
	 */
	public void setBody(Body body)
	{
		this.body = body;
	}
	/**
	 * returns true if a body is selected and false otherwise
	 * @return boolean value indicating wheter a body was chosen or not
	 */
	public boolean getBodyChosen()
	{
		return this.body != null;
	}
	/**
	 * returns either the chosen body in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with body options
	 */
	public LinkedList<Body> getBodyOptions()
	{
		if(this.getBodyChosen())
		{
			LinkedList<Body> toret = new LinkedList<Body>();
			toret.add(this.getBody());
			return toret;
		}
		else
		{
			return this.bodyOptions();
		}
	}
	/**
	 * returns a list with all the standard options for body
	 * @return a linked list with body options
	 */
	protected LinkedList<Body> bodyOptions()
	{
		return Body.options();
	}
	
	/**
	 * inspector for this.color
	 * @return this.color
	 */
	public Color getColor()
	{
		return this.color;
	}
	/**
	 * mutator for this.color
	 * @param color
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	/**
	 * returns true if a color is selected and false otherwise
	 * @return boolean value indicating wheter a color was chosen or not
	 */
	public boolean getColorChosen()
	{
		return this.color != null;
	}
	/**
	 * returns either the chosen color in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with color options
	 */
	public LinkedList<Color> getColorOptions()
	{
		if(this.getColorChosen())
		{
			LinkedList<Color> toret = new LinkedList<Color>();
			toret.add(this.getColor());
			return toret;
		}
		else
		{
			return this.colorOptions();
		}
	}
	/**
	 * returns a list with all the standard options for color
	 * @return a linked list with color options
	 */
	protected LinkedList<Color> colorOptions()
	{
		return Color.options();
	}
	
	/**
	 * inspector for this.engine
	 * @return this.engine
	 */
	public Engine getEngine()
	{
		return this.engine;
	}
	/**
	 * mutator for this.engine
	 * @param engine
	 */
	public void setEngine(Engine engine)
	{
		this.engine = engine;
	}
	/**
	 * returns true if a engine is selected and false otherwise
	 * @return boolean value indicating wheter a engine was chosen or not
	 */
	public boolean getEngineChosen()
	{
		return this.engine != null;
	}
	/**
	 * returns either the chosen engine in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with engine options
	 */
	public LinkedList<Engine> getEngineOptions()
	{
		if(this.getEngineChosen())
		{
			LinkedList<Engine> toret = new LinkedList<Engine>();
			toret.add(this.getEngine());
			return toret;
		}
		else
		{
			return this.engineOptions();
		}
	}
	/**
	 * returns a list with all the standard options for engine
	 * @return a linked list with engine options
	 */
	protected LinkedList<Engine> engineOptions()
	{
		return Engine.options();
	}

	/**
	 * inspector for this.gearbox
	 * @return this.gearbox
	 */
	public Gearbox getGearbox()
	{
		return this.gearbox;
	}
	/**
	 * mutator for this.gearbox
	 * @param gearbox
	 */
	public void setGearbox(Gearbox gearbox)
	{
		this.gearbox = gearbox;
	}
	/**
	 * returns true if a gearbox is selected and false otherwise
	 * @return boolean value indicating wheter a gearbox was chosen or not
	 */
	public boolean getGearboxChosen()
	{
		return this.gearbox != null;
	}
	/**
	 * returns either the chosen gearbox in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with gearbox options
	 */
	public LinkedList<Gearbox> getGearboxOptions()
	{
		if(this.getGearboxChosen())
		{
			LinkedList<Gearbox> toret = new LinkedList<Gearbox>();
			toret.add(this.getGearbox());
			return toret;
		}
		else
		{
			return this.gearboxOptions();
		}
	}
	/**
	 * returns a list with all the standard options for gearbox
	 * @return a linked list with gearbox options
	 */
	protected LinkedList<Gearbox> gearboxOptions()
	{
		return Gearbox.options();
	}

	/**
	 * inspector for this.seats
	 * @return this.seats
	 */
	public Seats getSeats()
	{
		return this.seats;
	}
	/**
	 * mutator for this.seats
	 * @param seats
	 */
	public void setSeats(Seats seats)
	{
		this.seats = seats;
	}
	/**
	 * returns true if a seats is selected and false otherwise
	 * @return boolean value indicating wheter a seats was chosen or not
	 */
	public boolean getSeatsChosen()
	{
		return this.seats != null;
	}
	/**
	 * returns either the chosen seats in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with seats options
	 */
	public LinkedList<Seats> getSeatsOptions()
	{
		if(this.getSeatsChosen())
		{
			LinkedList<Seats> toret = new LinkedList<Seats>();
			toret.add(this.getSeats());
			return toret;
		}
		else
		{
			return this.seatsOptions();
		}
	}
	/**
	 * returns a list with all the standard options for seats
	 * @return a linked list with seats options
	 */
	protected LinkedList<Seats> seatsOptions()
	{
		return Seats.options();
	}
	
	/**
	 * inspector for this.airco
	 * @return this.airco
	 */
	public Airco getAirco()
	{
		return this.airco;
	}
	/**
	 * mutator for this.airco
	 * @param airco
	 */
	public void setAirco(Airco airco)
	{
		this.airco = airco;
	}
	/**
	 * returns true if a airco is selected and false otherwise
	 * @return boolean value indicating wheter a airco was chosen or not
	 */
	public boolean getAircoChosen()
	{
		return this.airco != null;
	}
	/**
	 * returns either the chosen airco in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with airco options
	 */
	public LinkedList<Airco> getAircoOptions()
	{
		if(this.getAircoChosen())
		{
			LinkedList<Airco> toret = new LinkedList<Airco>();
			toret.add(this.getAirco());
			return toret;
		}
		else
		{
			return this.aircoOptions();
		}
	}
	/**
	 * returns a list with all the standard options for airco
	 * @return a linked list with airco options
	 */
	protected LinkedList<Airco> aircoOptions()
	{
		return Airco.options();
	}
	
	/**
	 * inspector for this.wheels
	 * @return this.wheels
	 */
	public Wheels getWheels()
	{
		return this.wheels;
	}
	/**
	 * mutator for this.wheels
	 * @param wheels
	 */
	public void setWheels(Wheels wheels)
	{
		this.wheels = wheels;
	}
	/**
	 * returns true if a wheels is selected and false otherwise
	 * @return boolean value indicating wheter a wheels was chosen or not
	 */
	public boolean getWheelsChosen()
	{
		return this.wheels != null;
	}
	/**
	 * returns either the chosen wheels in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with wheels options
	 */
	public LinkedList<Wheels> getWheelsOptions()
	{
		if(this.getWheelsChosen())
		{
			LinkedList<Wheels> toret = new LinkedList<Wheels>();
			toret.add(this.getWheels());
			return toret;
		}
		else
		{
			return this.wheelsOptions();
		}
	}
	/**
	 * returns a list with all the standard options for wheels
	 * @return a linked list with wheels options
	 */
	protected LinkedList<Wheels> wheelsOptions()
	{
		return Wheels.options();
	}

	
	/**
	 * inspector for this.spoiler
	 * @return this.spoiler
	 */
	public Spoiler getSpoiler()
	{
		return this.spoiler;
	}
	/**
	 * mutator for this.spoiler
	 * @param spoiler
	 */
	public void setSpoiler(Spoiler spoiler)
	{
		this.spoiler = spoiler;
	}
	/**
	 * returns true if a spoiler is selected and false otherwise
	 * @return boolean value indicating wheter a spoiler was chosen or not
	 */
	public boolean getSpoilerChosen()
	{
		return this.spoiler != null;
	}
	/**
	 * returns either the chosen spoiler in a list if one was chose or 
	 * a list with viable optios for this model specification
	 * @return a linked list with spoiler options
	 */
	public LinkedList<Spoiler> getSpoilerOptions()
	{
		if(this.getSpoilerChosen())
		{
			LinkedList<Spoiler> toret = new LinkedList<Spoiler>();
			toret.add(this.getSpoiler());
			return toret;
		}
		else
		{
			return this.spoilerOptions();
		}
	}
	/**
	 * returns a list with all the standard options for spoiler
	 * @return a linked list with spoiler options
	 */
	protected LinkedList<Spoiler> spoilerOptions()
	{
		return Spoiler.options();
	}
	
	/**
	 * inspector for the type field
	 * @return this.type
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * Adds the passed restriction to the chain of restrictions
	 * @param Restriction r
	 */
	public void addModelSpecificationRestriction(Restriction r)
	{
		if(this.restriction == null)
		{
			this.restriction = r;
		}
		else
		{
			this.restriction.setSuccessor(r);
		}
	}
	
	/**
	 * Checks wheter all restrictions pass for this specification but does
	 * not take into account whether all required parts have been selected
	 * @return true if all restriction checks pass
	 */
	protected boolean isPartialValid()
	{
		if(this.restriction == null)
		{
			return true;
		}
		else
		{
			return this.restriction.checkValidity();
		}
	}
	
	/**
	 * checks whether all required parts have been chosen but does not check
	 * wheter they go for this specific type of specification or in this
	 * configuration 
	 * @return true if all required parts have a value assigned to them
	 */
	protected boolean isBaseValid()
	{
		if(this.getAircoChosen())
		{
			if(!this.aircoOptions().contains(this.getAirco()))
				return false;
		}
		if(this.getSpoilerChosen())
		{
			if(!this.spoilerOptions().contains(this.getSpoiler()))
				return false;
		}
		return (this.bodyOptions().contains(this.body) &&
				this.colorOptions().contains(this.color) &&
				this.engineOptions().contains(this.engine) &&
				this.gearboxOptions().contains(this.gearbox) &&
				this.seatsOptions().contains(this.seats) &&
				this.wheelsOptions().contains(this.wheels));
}
	
	/**
	 * if partial is set to false then the entire specification is check for
	 * validity and the function returns true if the specification is valid
	 * if partial is set to true then only the restrictions are checked to see
	 * if their partial configuration is acceptible for this type of car
	 * @param partial
	 * @return
	 */
	public boolean isValid(boolean partial)
	{
		if(isPartialValid() && (partial || isBaseValid()))
		{
			return true;	
		}
		else
		{
			return false;
		}
	}
	
	public int getBuildingTimePerWorkstation()
	{
		return 50;
	}

	/**
	 * returns a string representation for this object
	 * @return a string representation for this object
	 */
	@Override
	public String toString() {
		return "Color: " + this.color + 
				"\nBody: " + this.body + 
				"\nEngine: " + this.engine + 
				"\nGearbox: " + this.gearbox + 
				"\nSeats: " + this.seats + 
				(this.getAircoChosen()?"\nAirco: " + this.airco:"")+
				"\nWheels: "+ this.wheels + 
				(this.getSpoilerChosen()?"\nSpoiler: " + this.spoiler:"");
	}
}
