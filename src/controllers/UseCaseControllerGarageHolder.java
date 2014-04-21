package controllers;

import java.util.ArrayList;
import java.util.List;

import user.User;
import util.LineReader;
import car.ModelASpec;
import car.ModelBSpec;
import car.ModelCSpec;
import car.OrderSpecification;
import car.Order;
import car.parts.Airco;
import car.parts.Body;
import car.parts.Carpart;
import car.parts.Color;
import car.parts.Engine;
import car.parts.Gearbox;
import car.parts.Seats;
import car.parts.Spoiler;
import car.parts.Wheels;

public class UseCaseControllerGarageHolder implements UseCaseController {
	private User user;
	private List<Order> upcommingOrders;
	private List<Order> prevOrders;
	private SystemController systemController;

	@Override
	public boolean guideUseCase(SystemController c) {
		systemController = c;
		user = systemController.getLoggedInUser();
		upcommingOrders = systemController.getScheduledOrdersFor(user);
		prevOrders = systemController.getFinishedOrdersFor(user);

		boolean running = true;

		while (running) {
			presentUserHistory();
			int input = presentMenu();
			if (input == 1)
				orderCar();
			if (input == 2)
				displayOrderDetails();
			if (input == 3)
				running = false;
		}

		return true;
	}

	private void presentUserHistory() {
		int index = 1;
		System.out.println("Upcomming orders:");
		for (Order o : upcommingOrders)
			System.out.println("(" + (index++) + ") order "
					+ o.toString().substring(0, 25) + "...");
		System.out.println("Finished orders:");
		for (Order o : prevOrders)
			System.out.println("(" + (index++) + ") " + o.toString().substring(0,25) + "...");
	}

	private int presentMenu() {
		System.out
				.println("Do you want to:\n(1) Order a car\n(2) View Order details\n(3) Log out");
		return Integer.parseInt(LineReader.readLine());
	}

	private void orderCar() {
		System.out.println("What type of car do you want to order?");
		System.out.println("(1) Model A\n(2) Model B\n(3) Model C");

		int choice = Integer.parseInt(LineReader.readLine());

		OrderSpecification spec = this.getSpecification(choice);

		setBody(spec);
		setColor(spec);
		setEngine(spec);
		setGearbox(spec);
		setSeats(spec);
		setAirco(spec);
		setWheels(spec);
		setSpoiler(spec);

		Order order = new Order(spec, user);

		System.out.println("Are you sure you want to place this order:");
		System.out.println(order);

		if (LineReader.readLine().toLowerCase().startsWith("n")) {
			System.out.println("Your order has been cancelled");
			return;
		}

		int expDelivery = systemController.placeOrder(order);

		System.out.println("Your order has been placed");
		System.out.println("We expect your order to be ready on: "
				+ expDelivery);

		upcommingOrders = systemController.getScheduledOrdersFor(user);
	}

	private OrderSpecification getSpecification(int n) {
		OrderSpecification spec = null;
		switch(n)
		{
		case 1: spec = new ModelASpec();break;
		case 2: spec = new ModelBSpec();break;
		case 3: spec = new ModelCSpec();break;
		default: throw new IllegalArgumentException("model type does not exist");
		}
		return spec;
	}
	
	private void setOption(OrderSpecification spec, Class<? extends Carpart> type, boolean mandatory) throws Exception
	{
		if(mandatory ==  false)
		{
			if(spec.getViableOptions(type).size() == 0)
			{
				return;
			}
			System.out.println("Would you like to order a " + type.toString() + "?");
			if(LineReader.readLine().toLowerCase().startsWith("n"))
				return;
		}
		else
		{
			if(spec.getViableOptions(type).size() == 0)
			{
				throw new Exception("mandatory part has no options");
			}
			if(spec.getViableOptions(type).size() == 1)
			{
				List<Carpart> l = new ArrayList<>(spec.getViableOptions(type));
				System.out.println("Added " + l.get(0));
				spec.add(l.get(0));
			}
		}
		while(!spec.containsPart(type))
		{
			System.out.println("What " + type.toString() + " would you like?");
			List<Carpart> options = new ArrayList<>(spec.getViableOptions(type));
			
			int index = 1;
			for(Carpart p : options)
				System.out.println("(" + (index++) + ") " + p);
			
			int choice = LineReader.readInt();
			
			if(choice > 0 && choice <= options.size())
				spec.add(options.get(choice - 1));
		}
	}

	private void setBody(OrderSpecification spec) {
		try {
			this.setOption(spec, Body.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setColor(OrderSpecification spec) {
		try {
			this.setOption(spec, Color.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setEngine(OrderSpecification spec) {
		try {
			this.setOption(spec, Engine.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setGearbox(OrderSpecification spec) {
		try {
			this.setOption(spec, Gearbox.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setSeats(OrderSpecification spec) {
		try {
			this.setOption(spec, Seats.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setAirco(OrderSpecification spec) {
		try {
			this.setOption(spec, Airco.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setWheels(OrderSpecification spec) {
		try {
			this.setOption(spec, Wheels.class, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setSpoiler(OrderSpecification spec) {
		try {
			this.setOption(spec, Spoiler.class, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayOrderDetails() {
		System.out.println("For what order do you want to display details?:");

		int order = LineReader.readInt();

		if (order > 0 && order <= upcommingOrders.size()) {
			System.out.println(upcommingOrders.get(order - 1));
		} else if (order > upcommingOrders.size()
				&& order <= upcommingOrders.size() + prevOrders.size() + 1) {
			System.out.println(prevOrders.get(order - 1
					- upcommingOrders.size()));
		}
	}
}
