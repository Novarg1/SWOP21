package system.usecases;

import java.util.LinkedList;

import system.SystemController;
import system.user.GarageHolder;
import system.user.User;
import car.CarAirco;
import car.CarBody;
import car.CarColor;
import car.CarEngine;
import car.CarGearbox;
import car.CarSeats;
import car.CarSpecification;
import car.CarWheels;
import car.StandardModel;

public class UseCaseGH3 implements UseCase 
{
	public UseCaseGH3()
	{		
		this.inputs = new LinkedList<String>();
	
		// login in user 2 - the garage holder
		inputs.add("user2");
		inputs.add(" ");
		
		// tell the system you want to order a car
		inputs.add("1");
		
		// the use case starts here
		// 2. the user wants to order a car
		inputs.add("1");
		
		// 4-6. the user specifies the car he wants to order
		inputs.add("1");
		inputs.add("1");
		inputs.add("2");
		inputs.add("1");
		inputs.add("2");
		inputs.add("2");
		
		// 6(b). the system processes the order
		inputs.add("no");
		
		// 7. the use case ends here
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
