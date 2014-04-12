package system.functionality;

import company.Company;
import car.CarPartAirco;
import car.CarPartBody;
import car.CarPartColor;
import car.CarPartEngine;
import car.CarPartGearbox;
import car.CarModel;
import car.CarOrder;
import car.CarPartSeats;
import car.CarModelSpecification;
import car.CarPartWheels;
import car.StandardModel;
import system.user.User;
import system.userinterface.UserInterface;

public class OrderProcessController implements FunctionalityController
{

	@Override
	public boolean provideFunctionality(UserInterface userInterface, User user, Company company) 
	{
		CarModel model = this.askForModel(userInterface);
		CarPartBody body = this.askForBody(userInterface);
		CarPartColor color = this.askForColor(userInterface);
		CarPartEngine engine = this.askForEngine(userInterface);
		CarPartGearbox gearbox = this.askForGearbox(userInterface);
		CarPartSeats seats = this.askForSeats(userInterface);
		CarPartAirco airco = this.askForAirco(userInterface);
		CarPartWheels wheels = this.askForWheels(userInterface);

		try 
		{
			CarModelSpecification spec = new CarModelSpecification(model, body, color, engine, gearbox, seats, airco, wheels);
			String input = userInterface.displayStringWithInput("You selected a car with options:\n" + spec + "\nDo you want to order this car?");
			if(input.startsWith("y"))
			{
				CarOrder order = new CarOrder(user, spec);
				company.placeOrder(order);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * asks the user what model he wants to order
	 * @return the requested CarModel
	 */
	private CarModel askForModel(UserInterface userInterface)
	{
//		String input = userInterface.displayStringWithInput("What type of wheels do you want?\n(1) comfort\n(2) sport");

//		if(input.equals("1"))return ;
//		if(input.equals("2"))return ;
		return new StandardModel();

//		throw new IllegalArgumentException();
	}

	/**
	 * asks the user what wheels he wants to order
	 * @return the requested CarWheels
	 */
	private CarPartWheels askForWheels(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of wheels do you want?\n(1) comfort\n(2) sport");

		if(input.equals("1"))return CarPartWheels.COMFORT;
		if(input.equals("2"))return CarPartWheels.SPORTS;

		throw new IllegalArgumentException();
	}

	/**
	 * asks the user what airoc he wants to order
	 * @return the requested CarAirco
	 */
	private CarPartAirco askForAirco(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of airco do you want?\n(1) manual\n(2) climate control");

		if(input.equals("1"))return CarPartAirco.MANUAL;
		if(input.equals("2"))return CarPartAirco.CLIMATE_CONTROL;

		throw new IllegalArgumentException();
	}

	/**
	 * asks the user what seats he wants to order
	 * @return the requested CarSeats
	 */
	private CarPartSeats askForSeats(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of seats do you desire?\n(1) vynil grey\n(2) black leather\n(3) white leather");

		if(input.equals("1"))return CarPartSeats.VINYL_GREY;
		if(input.equals("2"))return CarPartSeats.LEATHER_BLACK;
		if(input.equals("3"))return CarPartSeats.LEATHER_WHITE;

		throw new IllegalArgumentException();
	}

	/**
	 * asks the user what gearbox he wants to order
	 * @return the requested CarGearbox		 
	 */
	private CarPartGearbox askForGearbox(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of gearbox do you want?\n(1) manual\n(2) automatic");

		if(input.equals("1"))return CarPartGearbox.MANUAL;
		if(input.equals("2"))return CarPartGearbox.AUTOMATIC;

		throw new IllegalArgumentException();
	}

	/**
	 * asks the user which engine he wants to order
	 * @return the requested CarEngine
	 */
	private CarPartEngine askForEngine(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of engine do you desire?\n(1) standard\n(2) performance");

		if(input.equals("1"))return CarPartEngine.STANDARD;
		if(input.equals("2"))return CarPartEngine.PERFORMANCE;

		throw new IllegalArgumentException();
	}

	/**
	 * asks the user what color he wants to order
	 * @return the requested CarColor
	 */
	private CarPartColor askForColor(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What color did you want?\n(1) red\n(2) blue\n(3) black\n(4) white");

		if(input.equals("1"))return CarPartColor.RED;
		if(input.equals("2"))return CarPartColor.BLUE;
		if(input.equals("3"))return CarPartColor.BLACK;
		if(input.equals("4"))return CarPartColor.WHITE;

		throw new IllegalArgumentException();
	}

	/**
	 * asks the user which body he wants to order
	 * @return the requested CarBody
	 */
	private CarPartBody askForBody(UserInterface userInterface)
	{
		String input = userInterface.displayStringWithInput("What type of body did you want?\n(1) sedan\n(2) break");

		CarPartBody b;

		if(input.equals("1"))
		{
			b = CarPartBody.BODY_SEDAN;
		}
		else
		{
			if(input.equals("2"))
			{
				b = CarPartBody.BODY_BREAK;
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		return b;
	}

}
