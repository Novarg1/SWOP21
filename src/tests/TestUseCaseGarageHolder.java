package tests;

import org.junit.Test;

import car.CarAirco;
import car.CarBody;
import car.CarColor;
import car.CarEngine;
import car.CarGearbox;
import car.CarSeats;
import car.CarSpecification;
import car.CarWheels;
import car.StandardModel;
import system.SystemController;
import system.user.GarageHolder;
import system.user.User;
import system.user.UserController;
import system.userinterface.UserInterfaceUseCaseTester;
/**
 * Testing the garage holder use cases
 * @author jonathanlangens
 *
 */
public class TestUseCaseGarageHolder
{
	/**
	 * Garage holder use case under normal conditions
	 */
	@Test
	public void garageHolderNormalCase()
	{
		UserInterfaceUseCaseTester ui = new UserInterfaceUseCaseTester();

		// login in user 2 - the garage holder
		ui.addInput("user2");
		ui.addInput(" ");
		
		// tell the system you want to order a car
		ui.addInput("1");
		
		// the use case starts here
		// 2. the user wants to order a car
		ui.addInput("1");
		
		// 4-6. the user specifies the car he wants to order
		ui.addInput("1");
		ui.addInput("1");
		ui.addInput("2");
		ui.addInput("1");
		ui.addInput("2");
		ui.addInput("2");
		
		// 7. the system processes the order
		ui.addInput("yes");
		
		// adding the controller and running the program
		SystemController c = new SystemController(ui);
		UserController uc = c.displayLogin();
		uc.provideUI(ui);
		
		// if everything worked allright then a new car should have been added
		// to the schedule
		CarSpecification spec = new CarSpecification(new StandardModel(), CarBody.BODY_SEDAN, CarColor.RED,
												CarEngine.PERFORMANCE, CarGearbox.AUTOMATIC, CarSeats.VINYL_GREY, 
												CarAirco.CLIMATE_CONTROL, CarWheels.SPORTS);
		User user = new GarageHolder("user2", " ");
		assert(c.getCompany().getPendingOrders(user).get(0).toString().equals(spec.toString()));
	}
	
	/**
	 * Garage holder use case - alternative 1
	 * the user drops out right after being presented the opportunity to
	 * order a car
	 */
	@Test
	public void garageHolderAlternate1()
	{
		UserInterfaceUseCaseTester ui = new UserInterfaceUseCaseTester();

		// login in user 2 - the garage holder
		ui.addInput("user2");
		ui.addInput(" ");
		
		// tell the system you dont want to order a car
		ui.addInput("2");
		
		// adding the controller and running the program
		SystemController c = new SystemController(ui);
		UserController uc = c.displayLogin();
		uc.provideUI(ui);
		
		// if everything worked allright then a new car should not have been added
		// to the schedule
		User user = new GarageHolder("user2", " ");
		assert(c.getCompany().getPendingOrders(user).size()==0);
	}
	
	/**
	 * Garage holder use case - alternative 2
	 * the user fills out the entire form but at the last step decides not
	 * to order the car
	 */
	@Test
	public void garageHolderAlternate2()
	{
		UserInterfaceUseCaseTester ui = new UserInterfaceUseCaseTester();

		// login in user 2 - the garage holder
		ui.addInput("user2");
		ui.addInput(" ");
		
		// tell the system you want to order a car
		ui.addInput("1");
		
		// the use case starts here
		// 2. the user wants to order a car
		ui.addInput("1");
		
		// 4-6. the user specifies the car he wants to order
		ui.addInput("1");
		ui.addInput("1");
		ui.addInput("2");
		ui.addInput("1");
		ui.addInput("2");
		ui.addInput("2");
		
		// 7.b. the user does not want to order the car
		ui.addInput("no");
		
		// adding the controller and running the program
		SystemController c = new SystemController(ui);
		UserController uc = c.displayLogin();
		uc.provideUI(ui);
		
		// if everything worked allright then a new car should not have been added
		// to the schedule
		User user = new GarageHolder("user2", " ");
		assert(c.getCompany().getPendingOrders(user).size()==0);
	}
}
