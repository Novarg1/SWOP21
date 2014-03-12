package system.usecases;

import java.util.LinkedList;

import system.SystemController;
import system.user.GarageHolder;
import system.user.User;

public class UseCaseGH2 implements UseCase
{
	public UseCaseGH2()
	{
		this.inputs = new LinkedList<String>();
		
		// login in user 2 - the garage holder
		inputs.add("user2");
		inputs.add(" ");
		
		// the use case starts here
		// 1.(a) the user indicates that he wants to leave the overview
		//		  the usecase ends here
		inputs.add("2");
	}
	
	public String nextInput() 
	{
		String input = "";
		if(inputs.size()>0)
			input = inputs.pop();
		return input;
	}
	
	private LinkedList<String> inputs;

	@Override
	public boolean passTest(SystemController controller) {
		// TODO Auto-generated method stub
		try {
			User user = new GarageHolder("user2", " ");
			assert(controller.getCompany().getPendingOrders(user).size()==0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
									    
		return true;
	}
}
