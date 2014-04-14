package controllers;

import java.util.LinkedList;

import util.LineReader;
import car.CarModelSpecification;
import car.CarModelSpecificationA;
import car.CarOrder;
import car.CarPartAirco;
import car.CarPartBody;
import car.CarPartColor;
import car.CarPartEngine;
import car.CarPartGearbox;
import car.CarPartSeats;
import car.CarPartSpoiler;
import car.CarPartWheels;

public class UseCaseControllerGarageHolder implements UseCaseController
{
	private String user;
	private LinkedList<CarOrder> upcommingOrders;
	private LinkedList<CarOrder> prevOrders;
	private SystemController systemController;

	@Override
	public boolean guideUseCase(SystemController c) 
	{
		systemController = c;
	    user = systemController.getLoggedInUser();
		upcommingOrders = systemController.getScheduledOrdersFor(user);
		prevOrders = systemController.getFinishedOrdersFor(user);
		
		boolean running = true;
		
		while(running)
		{
			presentUserHistory();
			int input = presentMenu();
			if(input==1)
				orderCar();
			if(input==2)
				displayOrderDetails();
			if(input==3)
				running = false;
		}
		
		return true;
	}
	
	private void presentUserHistory()
	{
		int index = 1;
		System.out.println("Upcomming orders:");
		for(CarOrder o : upcommingOrders)
			System.out.println("(" + (index++) + ") order " + o.SPECIFICATION.getType());
		System.out.println("Finished orders:");
		for(CarOrder o : prevOrders)
			System.out.println("(" + (index++) + ") " + o);
	}
	
	private int presentMenu()
	{
		System.out.println("Do you want to:\n(1) Order a car\n(2) View Order details\n(3) Log out");
		return Integer.parseInt(LineReader.readLine());
	}
	
	private void orderCar()
	{
		System.out.println("What type of car do you want to order?");
		System.out.println("(1) Model A\n(2) Model B\n(3) Model C");
		
		int choice = Integer.parseInt(LineReader.readLine());
		
		CarModelSpecification spec = this.getSpecification(choice);
		
		setBody(spec);
		setColor(spec);
		setEngine(spec);
		setGearbox(spec);
		setSeats(spec);
		setAirco(spec);
		setWheels(spec);
		setSpoiler(spec);
		
		CarOrder order = new CarOrder(user, spec);
		
		int expDelivery = systemController.placeOrder(order);
		
		System.out.println("You have ordered:");
		System.out.println(order);
		System.out.println("We expect your order to be ready on: " + expDelivery);
		
		upcommingOrders = systemController.getScheduledOrdersFor(user);
	}
	
	private CarModelSpecification getSpecification(int n)
	{
		return new CarModelSpecificationA();
	}
	
	private void setBody(CarModelSpecification spec)
	{
		while(spec.getBodyChosen() == false)
		{
			System.out.println("What body type would you like?");
			
			int index = 1;
			for(CarPartBody b : spec.getBodyOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getBodyOptions().size())
			{
				spec.setBody(spec.getBodyOptions().get(choice - 1));
			}
		}
	}
	private void setColor(CarModelSpecification spec)
	{
		while(spec.getColorChosen() == false)
		{
			System.out.println("What color type would you like?");
			
			int index = 1;
			for(CarPartColor b : spec.getColorOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getColorOptions().size())
			{
				spec.setColor(spec.getColorOptions().get(choice - 1));
			}
		}
	}
	private void setEngine(CarModelSpecification spec)
	{
		while(spec.getEngineChosen() == false)
		{
			System.out.println("What engine type would you like?");
			
			int index = 1;
			for(CarPartEngine b : spec.getEngineOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getEngineOptions().size())
			{
				spec.setEngine(spec.getEngineOptions().get(choice - 1));
			}
		}
	}
	private void setGearbox(CarModelSpecification spec)
	{
		while(spec.getGearboxChosen() == false)
		{
			System.out.println("What Gearbox type would you like?");
			
			int index = 1;
			for(CarPartGearbox b : spec.getGearboxOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getGearboxOptions().size())
			{
				spec.setGearbox(spec.getGearboxOptions().get(choice - 1));
			}
		}
	}
	private void setSeats(CarModelSpecification spec)
	{
		while(spec.getSeatsChosen() == false)
		{
			System.out.println("What Seats type would you like?");
			
			int index = 1;
			for(CarPartSeats b : spec.getSeatsOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getSeatsOptions().size())
			{
				spec.setSeats(spec.getSeatsOptions().get(choice - 1));
			}
		}
	}	
	private void setAirco(CarModelSpecification spec)
	{
		boolean noAirco = false;
		
		if(spec.getAircoOptions().size() == 0)noAirco = true;
		
		while(spec.getAircoChosen() == false && noAirco == false)
		{
			System.out.println("Do you want an airco system? (y/n)");
			if(LineReader.readLine().toLowerCase().startsWith("n"))
			{
				noAirco = true;
			}
			else
			{
				System.out.println("What Airco type would you like?");
				
				int index = 1;
				for(CarPartAirco b : spec.getAircoOptions())
					System.out.println("(" + (index++) + ") " + b);
				
				int choice = LineReader.readInt(); 
				
				if(choice > 0 && choice <= spec.getAircoOptions().size())
				{
					spec.setAirco(spec.getAircoOptions().get(choice - 1));
				}
			}
		}
	}
	private void setWheels(CarModelSpecification spec)
	{
		while(spec.getWheelsChosen() == false)
		{
			System.out.println("What Wheels type would you like?");
			
			int index = 1;
			for(CarPartWheels b : spec.getWheelsOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getWheelsOptions().size())
			{
				spec.setWheels(spec.getWheelsOptions().get(choice - 1));
			}
		}
	}
	private void setSpoiler(CarModelSpecification spec)
	{
		boolean noSpoiler = false;
		
		if(spec.getSpoilerOptions().size() == 0)noSpoiler = true;
		
		while(spec.getSpoilerChosen() == false && noSpoiler == false) 
		{
			System.out.println("Do you want an Spoiler system? (y/n)");
			if(LineReader.readLine().toLowerCase().startsWith("n"))
			{
				noSpoiler = true;
			}
			else
			{
				System.out.println("What Spoiler type would you like?");
				
				int index = 1;
				for(CarPartSpoiler b : spec.getSpoilerOptions())
					System.out.println("(" + (index++) + ") " + b);
				
				int choice = LineReader.readInt(); 
				
				if(choice > 0 && choice <= spec.getSpoilerOptions().size())
				{
					spec.setSpoiler(spec.getSpoilerOptions().get(choice - 1));
				}
			}
		}
	}

	private void displayOrderDetails()
	{
		System.out.println("For what order do you want to display details?:");
		
		int order = LineReader.readInt();
		
		if(order > 0 && order <= upcommingOrders.size())
		{
			System.out.println(upcommingOrders.get(order - 1));
		}
		else if(order > upcommingOrders.size() && order <= upcommingOrders.size() + prevOrders.size() + 1)
		{
			System.out.println(prevOrders.get(order - 1 - upcommingOrders.size()));
		}
	}
}
