package car;

import java.util.Collections;
import java.util.Set;

//TODO check if parts are viable in all setters

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
	private Restriction restriction;

	public ModelSpecification(String model) {
		type = model;
		restriction = getModelRestriction();
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
		if (!getViableBodyOptions().contains(body)) {
			throw new IllegalArgumentException("invalid body");
		}
		this.body = body;
	}

	/**
	 * @return true if a body was chosen.
	 */
	public boolean bodyChosen() {
		return this.body != null;
	}

	/**
	 * @return a list with viable body options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if body was already chosen.
	 */
	public Set<Body.Options> getViableBodyOptions() {
		if (bodyChosen()) {
			return Collections.emptySet();
		}
		Set<Body.Options> result = getAllBodyOptions();
		for(Body.Options option : result) {
			this.body = new Body(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.body = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the body options that this model offers
	 */
	protected abstract Set<Body.Options> getAllBodyOptions();

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
		if (!getViableColorOptions().contains(color)) {
			throw new IllegalArgumentException("invalid color");
		}
		this.color = color;
	}

	/**
	 * returns true if a color is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a color was chosen or not
	 */
	public boolean colorChosen() {
		return this.color != null;
	}

	/**
	 * @return a list with viable color options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if color was already chosen.
	 */
	public Set<Color.Options> getViableColorOptions() {
		if (colorChosen()) {
			return Collections.emptySet();
		}
		Set<Color.Options> result = getAllColorOptions();
		for(Color.Options option : result) {
			this.color = new Color(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.color = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the color options that this model offers
	 */
	protected abstract Set<Color.Options> getAllColorOptions();

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
		if (!getViableEngineOptions().contains(engine)) {
			throw new IllegalArgumentException("invalid engine");
		}
		this.engine = engine;
	}

	/**
	 * returns true if a engine is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a engine was chosen or not
	 */
	public boolean engineChosen() {
		return this.engine != null;
	}

	/**
	 * @return a list with viable engine options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if engine was already chosen.
	 */
	public Set<Engine.Options> getViableEngineOptions() {
		if (engineChosen()) {
			return Collections.emptySet();
		}
		Set<Engine.Options> result = getAllEngineOptions();
		for(Engine.Options option : result) {
			this.engine = new Engine(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.engine = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the engine options that this model offers
	 */
	protected abstract Set<Engine.Options> getAllEngineOptions();

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
		if (!getViableGearboxOptions().contains(gearbox)) {
			throw new IllegalArgumentException("invalid gearbox");
		}
		this.gearbox = gearbox;
	}

	/**
	 * returns true if a gearbox is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a gearbox was chosen or not
	 */
	public boolean gearboxChosen() {
		return this.gearbox != null;
	}

	/**
	 * @return a list with viable gearbox options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if gearbox was already chosen.
	 */
	public Set<Gearbox.Options> getViableGearboxOptions() {
		if (gearboxChosen()) {
			return Collections.emptySet();
		}
		Set<Gearbox.Options> result = getAllGearboxOptions();
		for(Gearbox.Options option : result) {
			this.gearbox = new Gearbox(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.gearbox = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the gearbox options that this model offers
	 */
	protected abstract Set<Gearbox.Options> getAllGearboxOptions();

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
		if (!getViableSeatsOptions().contains(seats)) {
			throw new IllegalArgumentException("invalid seats");
		}
		this.seats = seats;
	}

	/**
	 * returns true if a seats is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a seats was chosen or not
	 */
	public boolean seatsChosen() {
		return this.seats != null;
	}

	/**
	 * @return a list with viable seats options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if seats was already chosen.
	 */
	public Set<Seats.Options> getViableSeatsOptions() {
		if (seatsChosen()) {
			return Collections.emptySet();
		}
		Set<Seats.Options> result = getAllSeatsOptions();
		for(Seats.Options option : result) {
			this.seats = new Seats(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.seats = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the seats options that this model offers
	 */
	protected abstract Set<Seats.Options> getAllSeatsOptions();

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
		if (!getViableAircoOptions().contains(airco)) {
			throw new IllegalArgumentException("invalid airco");
		}
		this.airco = airco;
	}

	/**
	 * returns true if a airco is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a airco was chosen or not
	 */
	public boolean aircoChosen() {
		return this.airco != null;
	}

	/**
	 * @return a list with viable airco options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if airco was already chosen.
	 */
	public Set<Airco.Options> getViableAircoOptions() {
		if (aircoChosen()) {
			return Collections.emptySet();
		}
		Set<Airco.Options> result = getAllAircoOptions();
		for(Airco.Options option : result) {
			this.airco = new Airco(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.airco = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the airco options that this model offers
	 */
	protected abstract Set<Airco.Options> getAllAircoOptions();

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
		if (!getViableWheelsOptions().contains(wheels)) {
			throw new IllegalArgumentException("invalid wheels");
		}
		this.wheels = wheels;
	}

	/**
	 * returns true if a wheels is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a wheels was chosen or not
	 */
	public boolean wheelsChosen() {
		return this.wheels != null;
	}

	/**
	 * @return a list with viable wheels options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if wheels was already chosen.
	 */
	public Set<Wheels.Options> getViableWheelsOptions() {
		if (wheelsChosen()) {
			return Collections.emptySet();
		}
		Set<Wheels.Options> result = getAllWheelsOptions();
		for(Wheels.Options option : result) {
			this.wheels = new Wheels(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.wheels = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the wheels options that this model offers
	 */
	protected abstract Set<Wheels.Options> getAllWheelsOptions();

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
		if (!getViableSpoilerOptions().contains(spoiler)) {
			throw new IllegalArgumentException("invalid spoiler");
		}
		this.spoiler = spoiler;
	}

	/**
	 * returns true if a spoiler is selected and false otherwise
	 * 
	 * @return boolean value indicating wheter a spoiler was chosen or not
	 */
	public boolean spoilerChosen() {
		return this.spoiler != null;
	}

	/**
	 * @return a list with viable spoiler options for this model specification.
	 *         This method takes the restrictions and previously selected
	 *         carparts into account. returns null if spoiler was already chosen.
	 */
	public Set<Spoiler.Options> getViableSpoilerOptions() {
		if (spoilerChosen()) {
			return Collections.emptySet();
		}
		Set<Spoiler.Options> result = getAllSpoilerOptions();
		for(Spoiler.Options option : result) {
			this.spoiler = new Spoiler(option);
			if(!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.spoiler = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the spoiler options that this model offers
	 */
	protected abstract Set<Spoiler.Options> getAllSpoilerOptions();

	/**
	 * Adds the passed restriction to the chain of restrictions
	 * 
	 * @param Restriction
	 *            r
	 */
	public void addRestriction(Restriction r) {
		if (this.restriction == null) {
			this.restriction = r;
		} else {
			this.restriction.setSuccessor(r);
		}
	}

	/**
	 * Checks wheter all restrictions pass for this specification but does not
	 * take parts into account that have not (yet) been selected.
	 */
	protected boolean isPartiallyValid() {
		return restriction == null || restriction.checkPartialValidity(this);
	}

	/**
	 * @return Restriction that states that all chosen parts must be supported
	 * 		by this model.
	 */
	private Restriction getModelRestriction() {
		return new Restriction() {

			@Override
			protected boolean isFulfilled(ModelSpecification spec) {
				return (!bodyChosen() || getAllBodyOptions().contains(getBody().getOptionType()))
						&& (!colorChosen() || getAllColorOptions().contains(getColor().getOptionType()))
						&& (!engineChosen() || getAllEngineOptions().contains(getEngine().getOptionType()))
						&& (!gearboxChosen() || getAllGearboxOptions().contains(getGearbox().getOptionType()))
						&& (!seatsChosen() || getAllSeatsOptions().contains(getSeats().getOptionType()))
						&& (!wheelsChosen() || getAllWheelsOptions().contains(getWheels().getOptionType()))
						&& (!aircoChosen() || getAllAircoOptions().contains(getAirco().getOptionType()))
						&& (!spoilerChosen() || getAllSpoilerOptions().contains(getSpoiler().getOptionType()));
			}

			@Override
			protected boolean isPartiallyFulfilled(ModelSpecification spec) {
				return this.isFulfilled(spec);
			}
		};
	}

	/**
	 * @return true if this is a valid and complete modelspecification
	 */
	public boolean isValid() {
		return restriction.checkValidity(this);
	}

	/**
	 * @return Estimated time in minutes that is spent in each workstation.
	 */
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
				+ (this.aircoChosen() ? "\nAirco: " + this.airco : "")
				+ "\nWheels: " + this.wheels
				+ (this.spoilerChosen() ? "\nSpoiler: " + this.spoiler : "");
	}

}
