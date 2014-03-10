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
		
		// login in user 2 - the garage holder and order a car
		inputs.add("user2");
		inputs.add(" ");
		
		// tell the system you want to order a car
		inputs.add("1");
		inputs.add("1");
		
		// specifiy the car
		inputs.add("1");
		inputs.add("1");
		inputs.add("2");
		inputs.add("1");
		inputs.add("2");
		inputs.add("2");
		inputs.add("yes");
		
		// log in as the manager - user 1
		inputs.add("user1");
		inputs.add(" ");
		
		
		// tell the system you want to advance the al
		inputs.add("1");
		
		// how much time has passed since the last...
		inputs.add("60");
		
		// go a head and advance the al
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
