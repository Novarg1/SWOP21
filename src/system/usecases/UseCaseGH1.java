package system.usecases;

import java.util.LinkedList;

import system.SystemController;
import system.user.GarageHolder;
import system.user.User;
import car.*;

/**
 * This is the use case for garage holder when everything goes as planned
 * the test succeeds when the ordered car is successfully found in schedule
 */
public class UseCaseGH1 implements UseCase
{
	public UseCaseGH1()
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
		
		// 7. the system processes the order
		inputs.add("yes");
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
			CarSpecification spec = new CarSpecification(new StandardModel(), CarBody.BODY_SEDAN, CarColor.RED,
												CarEngine.PERFORMANCE, CarGearbox.AUTOMATIC, CarSeats.VINYL_GREY, 
												CarAirco.CLIMATE_CONTROL, CarWheels.SPORTS);
			User user = new GarageHolder("user2", " ");
			assert(controller.getCompany().getPendingOrders(user).get(0).toString().equals(spec.toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
									    
		return true;
	}
}
