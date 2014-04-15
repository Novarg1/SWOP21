package car;

import java.util.List;

//TODO check if parts are viable in all setters
//TODO check restrictions in all get...Options()

/**
 * A carspecification contains specific parts that a car should have, along with
 * all the information of which parts this specific type of model might have and
 * with all the restrictions for this particular type of car. This car also has
 * logic to check whether all restrictions apply, and if the base conditions
 * (which are all parts must be selected except for airco and spoiler and all
 * selected options should be admissible for this type of car) are met.
 */
public abstract class ModelSpecification {

	private Body body = null;
	private Color color = null;
	private Engine engine = null;
	private Gearbox gearbox = null;
	private Seats seats = null;
	private Airco airco = null;
	private Wheels wheels = null;
	private Spoiler spoiler = null;

	protected final String type;
	private Restriction restriction = null;

	public ModelSpecification(String model) {
		type = model;
	}
	
	/**
	 * inspector for this.body
	 * 
	 * @return this.body
	 */
	public Body getBody() {
		return this.body;
	}

	/**
	 * mutator for this.body
	 * 
	 * @param body
	 */
	public void setBody(Body body) {
		if (body == null) {
			throw new IllegalArgumentException("invalid body");
		}
		this.body = body;
	}

	/**
	 * @return true if a body was chosen.
	 */
	public boolean BodyChosen() {
		return this.body != null;
	}

	/**
	 * @return a list with viable body options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if body was already chosen.
	 */
	public List<Body.Options> getViableBodyOptions() {
		if (this.BodyChosen()) {
			return null;
		}
		return null;// TODO
	}

	/**
	 * @return a list with all the body options, regardless of any possible
	 *         restrictions.
	 */
	protected abstract List<Body.Options> getAllBodyOptions();

	/**
	 * inspector for this.color
	 * 
	 * @return this.color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * mutator for this.color
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * returns true if a color is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a color was chosen or not
	 */
	public boolean getColorChosen() {
		return this.color != null;
	}

	/**
	 * returns either the chosen color in a list if one was chose or a list with
	 * viable optios for this model specification
	 * 
	 * @return a linked list with color options
	 */
	public List<Color.Options> getViableColorOptions() {
		if (this.getColorChosen()) {
			return null;
		}
		return null; // TODO
	}

	/**
	 * returns a list with all the standard options for color
	 * 
	 * @return a linked list with color options
	 */
	protected abstract List<Color.Options> getAllColorOptions();

	/**
	 * inspector for this.engine
	 * 
	 * @return this.engine
	 */
	public Engine getEngine() {
		return this.engine;
	}

	/**
	 * mutator for this.engine
	 * 
	 * @param engine
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	/**
	 * returns true if a engine is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a engine was chosen or not
	 */
	public boolean getEngineChosen() {
		return this.engine != null;
	}

	/**
	 * returns either the chosen engine in a list if one was chose or a list
	 * with viable optios for this model specification
	 * 
	 * @return a linked list with engine options
	 */
	public List<Engine.Options> getViableEngineOptions() {
		if (this.getEngineChosen()) {
			return null;
		}
		return null; // TODO
	}

	/**
	 * returns a list with all the standard options for engine
	 * 
	 * @return a linked list with engine options
	 */
	protected abstract List<Engine.Options> getAllEngineOptions();

	/**
	 * inspector for this.gearbox
	 * 
	 * @return this.gearbox
	 */
	public Gearbox getGearbox() {
		return this.gearbox;
	}

	/**
	 * mutator for this.gearbox
	 * 
	 * @param gearbox
	 */
	public void setGearbox(Gearbox gearbox) {
		this.gearbox = gearbox;
	}

	/**
	 * returns true if a gearbox is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a gearbox was chosen or not
	 */
	public boolean getGearboxChosen() {
		return this.gearbox != null;
	}

	/**
	 * returns either the chosen gearbox in a list if one was chose or a list
	 * with viable optios for this model specification
	 * 
	 * @return a linked list with gearbox options
	 */
	public List<Gearbox.Options> getViableGearboxOptions() {
		if (this.getGearboxChosen()) {
			return null;
		}
		return null; // TODO
	}

	/**
	 * returns a list with all the standard options for gearbox
	 * 
	 * @return a linked list with gearbox options
	 */
	protected abstract List<Gearbox.Options> getAllGearboxOptions();

	/**
	 * inspector for this.seats
	 * 
	 * @return this.seats
	 */
	public Seats getSeats() {
		return this.seats;
	}

	/**
	 * mutator for this.seats
	 * 
	 * @param seats
	 */
	public void setSeats(Seats seats) {
		this.seats = seats;
	}

	/**
	 * returns true if a seats is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a seats was chosen or not
	 */
	public boolean getSeatsChosen() {
		return this.seats != null;
	}

	/**
	 * returns either the chosen seats in a list if one was chose or a list with
	 * viable optios for this model specification
	 * 
	 * @return a linked list with seats options
	 */
	public List<Seats.Options> getViableSeatsOptions() {
		if (this.getSeatsChosen()) {
			return null;
		}
		return null; // TODO
	}

	/**
	 * returns a list with all the standard options for seats
	 * 
	 * @return a linked list with seats options
	 */
	protected abstract List<Seats.Options> getAllSeatsOptions();

	/**
	 * inspector for this.airco
	 * 
	 * @return this.airco
	 */
	public Airco getAirco() {
		return this.airco;
	}

	/**
	 * mutator for this.airco
	 * 
	 * @param airco
	 */
	public void setAirco(Airco airco) {
		this.airco = airco;
	}

	/**
	 * returns true if a airco is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a airco was chosen or not
	 */
	public boolean getAircoChosen() {
		return this.airco != null;
	}

	/**
	 * returns either the chosen airco in a list if one was chose or a list with
	 * viable optios for this model specification
	 * 
	 * @return a linked list with airco options
	 */
	public List<Airco.Options> getViableAircoOptions() {
		if (this.getAircoChosen()) {
			return null;
		}
		return null; // TODO
	}

	/**
	 * returns a list with all the standard options for airco
	 * 
	 * @return a linked list with airco options
	 */
	protected abstract List<Airco.Options> getAllAircoOptions();

	/**
	 * inspector for this.wheels
	 * 
	 * @return this.wheels
	 */
	public Wheels getWheels() {
		return this.wheels;
	}

	/**
	 * mutator for this.wheels
	 * 
	 * @param wheels
	 */
	public void setWheels(Wheels wheels) {
		this.wheels = wheels;
	}

	/**
	 * returns true if a wheels is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a wheels was chosen or not
	 */
	public boolean getWheelsChosen() {
		return this.wheels != null;
	}

	/**
	 * returns either the chosen wheels in a list if one was chose or a list
	 * with viable optios for this model specification
	 * 
	 * @return a linked list with wheels options
	 */
	public List<Wheels.Options> getViableWheelsOptions() {
		if (this.getWheelsChosen()) {
			return null;
		}
		return null; // TODO
	}

	/**
	 * returns a list with all the standard options for wheels
	 * 
	 * @return a linked list with wheels options
	 */
	protected abstract List<Wheels.Options> getAllWheelsOptions();

	/**
	 * inspector for this.spoiler
	 * 
	 * @return this.spoiler
	 */
	public Spoiler getSpoiler() {
		return this.spoiler;
	}

	/**
	 * mutator for this.spoiler
	 * 
	 * @param spoiler
	 */
	public void setSpoiler(Spoiler spoiler) {
		this.spoiler = spoiler;
	}

	/**
	 * returns true if a spoiler is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a spoiler was chosen or not
	 */
	public boolean getSpoilerChosen() {
		return this.spoiler != null;
	}

	/**
	 * returns either the chosen spoiler in a list if one was chose or a list
	 * with viable optios for this model specification
	 * 
	 * @return a linked list with spoiler options
	 */
	public List<Spoiler.Options> getViableSpoilerOptions() {
		if (this.getSpoilerChosen()) {
			return null;
		}
		return null; // TODO
	}

	/**
	 * returns a list with all the standard options for spoiler
	 * 
	 * @return a linked list with spoiler options
	 */
	protected abstract List<Spoiler.Options> getAllSpoilerOptions();

	/**
	 * Adds the passed restriction to the chain of restrictions
	 * 
	 * @param Restriction
	 *            r
	 */
	public void addModelSpecificationRestriction(Restriction r) {
		if (this.restriction == null) {
			this.restriction = r;
		} else {
			this.restriction.setSuccessor(r);
		}
	}

	/**
	 * Checks wheter all restrictions pass for this specification but does not
	 * take into account whether all required parts have been selected
	 * 
	 * @return true if all restriction checks pass
	 */
	protected boolean isPartialValid() {
		return restriction == null ? true : restriction.checkValidity();
	}

	/**
	 * checks whether all required parts have been chosen but does not check
	 * wheter they go for this specific type of specification or in this
	 * configuration
	 * 
	 * @return true if all required parts have a value assigned to them
	 */
	protected boolean isBaseValid() {
		if (this.getAircoChosen()) {
			if (!this.getAllAircoOptions().contains(this.getAirco()))
				return false;
		}
		if (this.getSpoilerChosen()) {
			if (!this.getAllSpoilerOptions().contains(this.getSpoiler()))
				return false;
		}
		return (this.getAllBodyOptions().contains(this.body)
				&& this.getAllColorOptions().contains(this.color)
				&& this.getAllEngineOptions().contains(this.engine)
				&& this.getAllGearboxOptions().contains(this.gearbox)
				&& this.getAllSeatsOptions().contains(this.seats) && this
				.getAllWheelsOptions().contains(this.wheels));
	}

	/**
	 * if partial is set to false then the entire specification is check for
	 * validity and the function returns true if the specification is valid if
	 * partial is set to true then only the restrictions are checked to see if
	 * their partial configuration is acceptible for this type of car
	 * 
	 * @param partial
	 * @return
	 */
	public boolean isValid(boolean partial) {
		return isPartialValid() && (partial || isBaseValid());
	}

	public abstract int getBuildingTimePerWorkstation();

	/**
	 * returns a string representation for this object
	 * 
	 * @return a string representation for this object
	 */
	@Override
	public String toString() {
		return "Color: " + this.color + "\nBody: " + this.body + "\nEngine: "
				+ this.engine + "\nGearbox: " + this.gearbox + "\nSeats: "
				+ this.seats
				+ (this.getAircoChosen() ? "\nAirco: " + this.airco : "")
				+ "\nWheels: " + this.wheels
				+ (this.getSpoilerChosen() ? "\nSpoiler: " + this.spoiler : "");
	}
}
