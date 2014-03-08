package system.functionality;

import company.Company;
import car.CarAirco;
import car.CarBody;
import car.CarColor;
import car.CarEngine;
import car.CarGearbox;
import car.CarModel;
import car.CarOrder;
import car.CarSeats;
import car.CarSpecification;
import car.CarWheels;
import car.StandardModel;
import system.user.User;
import system.userinterface.UserInterface;

public class OrderProcessController implements FunctionalityController
{
	public OrderProcessController()
	{
	}

	@Override
	public boolean provideFunctionality(UserInterface userInterface, User user, Company company) 
	{
		CarModel model = this.askForModel(userInterface);
		CarBody body = this.askForBody(userInterface);
		CarColor color = this.askForColor(userInterface);
		CarEngine engine = this.askForEngine(userInterface);
		CarGearbox gearbox = this.askForGearbox(userInterface);
		CarSeats seats = this.askForSeats(userInterface);
		CarAirco airco = this.askForAirco(userInterface);
		CarWheels wheels = this.askForWheels(userInterface);

		try 
		{
			CarSpecification spec = new CarSpecification(model, body, color, engine, gearbox, seats, airco, wheels);
			String input = userInterface.displayStringWithInput("You selected a car with options:\n" + spec + "\nDo you want to order this car?");
			if(input.startsWith("y"))
			{
				CarOrder order = new CarOrder(user, spec);
				// TODO place a carorder here
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return false;
	}

	private CarModel askForModel(UserInterface userInterface)
	{
//		String input = userInterface.displayStringWithInput("What type of wheels do you want?\n(1) comfort\n(2) sport");

//		if(input.equals("1"))return ;
//		if(input.equals("2"))return ;
		return new StandardModel();

//		throw new IllegalArgumentException();
	}

	private CarWheels askForWheels(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of wheels do you want?\n(1) comfort\n(2) sport");

		if(input.equals("1"))return CarWheels.COMFORT;
		if(input.equals("2"))return CarWheels.SPORTS;

		throw new IllegalArgumentException();
	}

	private CarAirco askForAirco(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of airco do you want?\n(1) manual\n(2) climate control");

		if(input.equals("1"))return CarAirco.MANUAL;
		if(input.equals("2"))return CarAirco.CLIMATE_CONTROL;

		throw new IllegalArgumentException();
	}

	private CarSeats askForSeats(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of seats do you desire?\n(1) vynil grey\n(2) black leather\n(3) white leather");

		if(input.equals("1"))return CarSeats.VINYL_GREY;
		if(input.equals("2"))return CarSeats.LEATHER_BLACK;
		if(input.equals("3"))return CarSeats.LEATHER_WHITE;

		throw new IllegalArgumentException();
	}

	private CarGearbox askForGearbox(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of gearbox do you want?\n(1) manual\n(2) automatic");

		if(input.equals("1"))return CarGearbox.MANUAL;
		if(input.equals("2"))return CarGearbox.AUTOMATIC;

		throw new IllegalArgumentException();
	}

	private CarEngine askForEngine(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of engine do you desire?\n(1) standard\n(2) performance");

		if(input.equals("1"))return CarEngine.STANDARD;
		if(input.equals("2"))return CarEngine.PERFORMANCE;

		throw new IllegalArgumentException();
	}

	private CarColor askForColor(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What color did you want?\n(1) red\n(2) blue\n(3) black\n(4) white");

		if(input.equals("1"))return CarColor.RED;
		if(input.equals("2"))return CarColor.BLUE;
		if(input.equals("3"))return CarColor.BLACK;
		if(input.equals("4"))return CarColor.WHITE;

		throw new IllegalArgumentException();
	}

	private CarBody askForBody(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of body did you want?\n(1) sedan\n(2) break");

		CarBody b;

		if(input.equals("1"))
		{
			b = CarBody.BODY_SEDAN;
		}
		else
		{
			if(input.equals("2"))
			{
				b = CarBody.BODY_BREAK;
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		return b;
	}

}
