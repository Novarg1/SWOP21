package car;
/**
 * if a car has a sports body it also must have a spoiler
 * @author jonathanlangens
 *
 */
public class RestricitionSport extends Restriction
{

	public RestricitionSport(
			Model specification) {
		super(specification);
		// TODO Auto-generated constructor stub
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
		if(spec.getBodyChosen() && spec.getBody() == Body.BODY_SPORT)
		{
			if(spec.getSpoilerChosen())
			{
				if(spec.getEngineChosen() &&
						(spec.getEngine()==Engine.PERFORMANCE ||
							spec.getEngine()==Engine.ULTRA))
				{
					return this.checkSuccessorValidity();
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return this.checkSuccessorValidity();
		}
	}

}
