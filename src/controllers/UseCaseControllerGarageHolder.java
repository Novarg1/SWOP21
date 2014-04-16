package controllers;

import java.util.List;

import util.LineReader;
import car.ModelSpecification;
import car.ModelASpec;
import car.CarOrder;
import car.Airco;
import car.Body;
import car.Color;
import car.Engine;
import car.Gearbox;
import car.Seats;
import car.Spoiler;
import car.Wheels;

public class UseCaseControllerGarageHolder implements UseCaseController
{
	private String user;
	private List<CarOrder> upcommingOrders;
	private List<CarOrder> prevOrders;
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
		
		ModelSpecification spec = this.getSpecification(choice);
		
		setBody(spec);
		setColor(spec);
		setEngine(spec);
		setGearbox(spec);
		setSeats(spec);
		setAirco(spec);
		setWheels(spec);
		setSpoiler(spec);
		
		CarOrder order = new CarOrder(user, spec);
		
		System.out.println("Are you sure you want to place this order:");
		System.out.println(order);
		
		if(LineReader.readLine().toLowerCase().startsWith("n"))
		{
			System.out.println("Your order has been cancelled");
			return;
		}
		
		int expDelivery = systemController.placeOrder(order);
		
		System.out.println("Your order has been placed");
		System.out.println("We expect your order to be ready on: " + expDelivery);
		
		upcommingOrders = systemController.getScheduledOrdersFor(user);
	}
	
	private ModelSpecification getSpecification(int n)
	{
		return new ModelASpec();
	}
	
	private void setBody(ModelSpecification spec)
	{
		while(spec.bodyChosen() == false)
		{
			System.out.println("What body type would you like?");
			
			int index = 1;
			for(Body b : spec.getViableBodyOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getViableBodyOptions().size())
			{
				spec.setBody(spec.getViableBodyOptions().get(choice - 1));
			}
		}
	}
	private void setColor(ModelSpecification spec)
	{
		while(spec.colorChosen() == false)
		{
			System.out.println("What color type would you like?");
			
			int index = 1;
			for(Color b : spec.getViableColorOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getViableColorOptions().size())
			{
				spec.setColor(spec.getViableColorOptions().get(choice - 1));
			}
		}
	}
	private void setEngine(ModelSpecification spec)
	{
		while(spec.engineChosen() == false)
		{
			System.out.println("What engine type would you like?");
			
			int index = 1;
			for(Engine b : spec.getViableEngineOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getViableEngineOptions().size())
			{
				spec.setEngine(spec.getViableEngineOptions().get(choice - 1));
			}
		}
	}
	private void setGearbox(ModelSpecification spec)
	{
		while(spec.gearboxChosen() == false)
		{
			System.out.println("What Gearbox type would you like?");
			
			int index = 1;
			for(Gearbox b : spec.getViableGearboxOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getViableGearboxOptions().size())
			{
				spec.setGearbox(spec.getViableGearboxOptions().get(choice - 1));
			}
		}
	}
	private void setSeats(ModelSpecification spec)
	{
		while(spec.seatsChosen() == false)
		{
			System.out.println("What Seats type would you like?");
			
			int index = 1;
			for(Seats b : spec.getViableSeatsOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getViableSeatsOptions().size())
			{
				spec.setSeats(spec.getViableSeatsOptions().get(choice - 1));
			}
		}
	}	
	private void setAirco(ModelSpecification spec)
	{
		boolean noAirco = false;
		
		if(spec.getViableAircoOptions().size() == 0)noAirco = true;
		
		while(spec.aircoChosen() == false && noAirco == false)
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
				for(Airco b : spec.getViableAircoOptions())
					System.out.println("(" + (index++) + ") " + b);
				
				int choice = LineReader.readInt(); 
				
				if(choice > 0 && choice <= spec.getViableAircoOptions().size())
				{
					spec.setAirco(spec.getViableAircoOptions().get(choice - 1));
				}
			}
		}
	}
	private void setWheels(ModelSpecification spec)
	{
		while(spec.wheelsChosen() == false)
		{
			System.out.println("What Wheels type would you like?");
			
			int index = 1;
			for(Wheels b : spec.getViableWheelsOptions())
				System.out.println("(" + (index++) + ") " + b);
			
			int choice = LineReader.readInt(); 
			
			if(choice > 0 && choice <= spec.getViableWheelsOptions().size())
			{
				spec.setWheels(spec.getViableWheelsOptions().get(choice - 1));
			}
		}
	}
	private void setSpoiler(ModelSpecification spec)
	{
		boolean noSpoiler = false;
		
		if(spec.getViableSpoilerOptions().size() == 0)noSpoiler = true;
		
		while(spec.spoilerChosen() == false && noSpoiler == false) 
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
				for(Spoiler b : spec.getViableSpoilerOptions())
					System.out.println("(" + (index++) + ") " + b);
				
				int choice = LineReader.readInt(); 
				
				if(choice > 0 && choice <= spec.getViableSpoilerOptions().size())
				{
					spec.setSpoiler(spec.getViableSpoilerOptions().get(choice - 1));
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
