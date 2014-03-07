package system.functionality;

/* Factory pattern
 * returns a fully instantiated functionality controller
 */
public class FunctionalityControllerFactory 
{

	public static FunctionalityController getControllerFor(Functionality f)
	{
		switch(f)
		{
		case FUNCTIONALITY_ASSEMBLY_LINE:
			return new AssemblyLineController();
		case FUNCTIONALITY_ORDER_PROCESS:
			return new OrderProcessController();
		case FUNCTIONALITY_WORK_STATION:
			return new WorkStationController();
		default:
			throw new IllegalArgumentException();
		}
	}
}
