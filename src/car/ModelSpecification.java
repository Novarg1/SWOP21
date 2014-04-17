package car;

import java.util.Collections;
import java.util.Set;

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

	private final int buildingTime;
	private static final int DEFAULT_BUILDING_TIME = 60;

	/**
	 * model with default building time per part
	 */
	public ModelSpecification(String model) {
		this(model, DEFAULT_BUILDING_TIME);
	}

	public ModelSpecification(String model, int buildingTime) {
		type = model;
		restriction = getModelRestriction();
		this.buildingTime = buildingTime;
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
		if (!getViableBodies().contains(body)) {
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
	 *         carparts into account. returns empty set if body was already
	 *         chosen.
	 */
	public Set<Body> getViableBodies() {
		if (bodyChosen()) {
			return Collections.emptySet();
		}
		Set<Body> result = getAllBodies();
		for (Body option : result) {
			this.body = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.body = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the body options that this model offers
	 */
	protected abstract Set<Body> getAllBodies();

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
		if (!getViableColors().contains(color)) {
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
	public Set<Color> getViableColors() {
		if (colorChosen()) {
			return Collections.emptySet();
		}
		Set<Color> result = getAllColors();
		for (Color option : result) {
			this.color = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.color = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the color options that this model offers
	 */
	protected abstract Set<Color> getAllColors();

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
		if (!getViableEngines().contains(engine)) {
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
	public Set<Engine> getViableEngines() {
		if (engineChosen()) {
			return Collections.emptySet();
		}
		Set<Engine> result = getAllEngines();
		for (Engine option : result) {
			this.engine = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.engine = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the engine options that this model offers
	 */
	protected abstract Set<Engine> getAllEngines();

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
		if (!getViableGearboxes().contains(gearbox)) {
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
	 *         carparts into account. returns null if gearbox was already
	 *         chosen.
	 */
	public Set<Gearbox> getViableGearboxes() {
		if (gearboxChosen()) {
			return Collections.emptySet();
		}
		Set<Gearbox> result = getAllGearboxes();
		for (Gearbox option : result) {
			this.gearbox = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.gearbox = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the gearbox options that this model offers
	 */
	protected abstract Set<Gearbox> getAllGearboxes();

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
		if (!getViableSeats().contains(seats)) {
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
	public Set<Seats> getViableSeats() {
		if (seatsChosen()) {
			return Collections.emptySet();
		}
		Set<Seats> result = getAllSeats();
		for (Seats option : result) {
			this.seats = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.seats = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the seats options that this model offers
	 */
	protected abstract Set<Seats> getAllSeats();

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
		if (!getViableAircos().contains(airco)) {
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
	public Set<Airco> getViableAircos() {
		if (aircoChosen()) {
			return Collections.emptySet();
		}
		Set<Airco> result = getAllAircos();
		for (Airco option : result) {
			this.airco = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.airco = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the airco options that this model offers
	 */
	protected abstract Set<Airco> getAllAircos();

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
		if (!getViableWheels().contains(wheels)) {
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
	public Set<Wheels> getViableWheels() {
		if (wheelsChosen()) {
			return Collections.emptySet();
		}
		Set<Wheels> result = getAllWheels();
		for (Wheels option : result) {
			this.wheels = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.wheels = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the wheels options that this model offers
	 */
	protected abstract Set<Wheels> getAllWheels();

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
		if (!getViableSpoilers().contains(spoiler)) {
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
	 *         carparts into account. returns null if spoiler was already
	 *         chosen.
	 */
	public Set<Spoiler> getViableSpoilers() {
		if (spoilerChosen()) {
			return Collections.emptySet();
		}
		Set<Spoiler> result = getAllSpoilers();
		for (Spoiler option : result) {
			this.spoiler = option;
			if (!this.isPartiallyValid()) {
				result.remove(option);
			}
			this.spoiler = null;
		}
		return result;
	}

	/**
	 * returns a set containing all the spoiler options that this model offers
	 */
	protected abstract Set<Spoiler> getAllSpoilers();

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
	 *         by this model.
	 */
	private Restriction getModelRestriction() {
		return new Restriction() {

			@Override
			protected boolean isFulfilled(ModelSpecification spec) {
				return (!bodyChosen() || getAllBodies().contains(getBody()))
						&& (!colorChosen() || getAllColors().contains(getColor()))
						&& (!engineChosen() || getAllEngines().contains(getEngine()))
						&& (!gearboxChosen() || getAllGearboxes().contains(getGearbox()))
						&& (!seatsChosen() || getAllSeats().contains(getSeats()))
						&& (!wheelsChosen() || getAllWheels().contains(getWheels()))
						&& (!aircoChosen() || getAllAircos().contains(getAirco()))
						&& (!spoilerChosen() || getAllSpoilers().contains(getSpoiler()));
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
	public int getBuildingTimePerWorkstation() {
		return buildingTime;
	}

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
