package car;

/**
 * if a car has a sports body it also must have a spoiler
 * @author jonathanlangens
 *
 */
public class RestricitionSport extends Restriction {

	public RestricitionSport(ModelSpecification specification) {
		super(specification);
	}

	/**
	 * if this car has a sports body it must have a spoiler and either a performance
	 * engine or an ultra engine
	 * @return true if the car has either a sports body and a spoiler and 
	 * 		   an ultra or performance engine or if it does not have a sports body
	 *         
	 */
	@Override
	public boolean checkValidity() {
		if(spec.BodyChosen() && spec.getBody().getOptionType() == Body.Options.SPORT) {
			if(spec.getSpoilerChosen() &&
					spec.getEngineChosen() &&
					(spec.getEngine().getOptionType()==Engine.Options.PERFORMANCE ||
					spec.getEngine().getOptionType()==Engine.Options.ULTRA)) {
				return this.checkSuccessorValidity();
			}
			return false;
		}
		return this.checkSuccessorValidity();
	}

}
