package car;

import java.util.LinkedList;

public class ModelA extends Model
{
	public ModelA()
	{
		this.addModelSpecificationRestriction(new RestricitionSport(this));
		this.type = "Model A";
	}
	
	protected LinkedList<Body> bodyOptions() {
		LinkedList<Body> toret = new LinkedList<Body>();
		toret.add(Body.BODY_BREAK);
		toret.add(Body.BODY_SEDAN);
		return toret;
	}
	
	protected LinkedList<Color> colorOptions()
	{
		LinkedList<Color> toret = new LinkedList<Color>();
		toret.add(Color.BLACK);
		toret.add(Color.RED);
		toret.add(Color.BLUE);
		toret.add(Color.WHITE);
		return toret;
	}
	
	protected LinkedList<Engine> engineOptions()
	{
		LinkedList<Engine> toret = new LinkedList<Engine>();
		toret.add(Engine.STANDARD);
		toret.add(Engine.PERFORMANCE);
		return toret;
	}
	
	protected LinkedList<Spoiler> spoilerOptions()
	{
		return new LinkedList<Spoiler>();
	}

	@Override
	public Model clone() {
		ModelA toret = new ModelA();
		toret.setBody(body);
		toret.setAirco(airco);
		return toret;
	}
	
	public int getBuildingTimePerWorkstation()
	{
		return 50;
	}

}
