package car;
/**
 * this class represents a specific restriction and a chain to manage all
 * restrictions
 * 
 * @author jonathanlangens
 *
 */
public abstract class CarModelSpecificationRestriction 
{
	protected CarModelSpecification spec;
	protected CarModelSpecificationRestriction successor = null;
	
	/**
	 * the constructor for a restriction and the specification to which it should
	 * apply
	 * @param specification
	 */
	public CarModelSpecificationRestriction(CarModelSpecification specification) throws IllegalArgumentException
	{
		if(specification == null)
			throw new IllegalArgumentException();
		this.spec = specification;
	}
	
	/**
	 * adds the successor to the chain of restrictions
	 * @param successor
	 */
	public void setSuccessor(CarModelSpecificationRestriction successor)
	{
		if(this.successor == null)
		{
			this.successor = successor;
		}
		else
		{
			this.successor.setSuccessor(successor);
		}
	}
	
	/**
	 * if there is a successor we check whether it is valid for this
	 * specification or not
	 * @return
	 */
	public boolean checkSuccessorValidity()
	{
		return (this.successor != null)?this.successor.checkValidity():true;
	}
	
	/**
	 * abstract method to be overriden for each specific restriction
	 * @return
	 */
	public abstract boolean checkValidity();
}
