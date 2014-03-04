package system.usecases;

import java.util.LinkedList;

import system.userinterface.UserInterfaceUseCaseTester;
/* This usecase should demo the system functionality
 * 
 */
public class UseCaseDemo implements UseCase
{
	public UseCaseDemo()
	{
		this.inputs = new LinkedList<String>();
		
		// login in user 1
		inputs.add("user1");
		inputs.add(" ");
		
		// tell the system you want to advance the al
		inputs.add("1");
		inputs.add("yes");
		
		
		// login in user 2
		inputs.add("user2");
		inputs.add(" ");
		
		// login in user 3
		inputs.add("user3");
		inputs.add(" ");		
	}
	
	@Override
	public String nextInput() 
	{
		String input = "";
		if(inputs.size()>0)
			input = inputs.pop();
		return input;
	}
	
	private LinkedList<String> inputs;
}
