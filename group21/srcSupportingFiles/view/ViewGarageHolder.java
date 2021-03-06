package view;

import java.util.List;

import user.User;
import util.LineReader;
import vehicle.order.ModelA;
import vehicle.order.ModelB;
import vehicle.order.ModelC;
import vehicle.order.ModelX;
import vehicle.order.ModelY;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import controllers.SystemController;

/**
 * Provides a UI to let a garage holder manage placed orders and to place orders
 * @author jonathanlangens
 *
 */
public class ViewGarageHolder extends ViewOrderForm
{	
	protected ViewGarageHolder(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	private User user;
	private List<Order> upcomingOrders;
	private List<Order> prevOrders;
	
	public boolean show() 
	{
		user = systemController.getLoggedInUser();
		upcomingOrders = systemController.getScheduledOrdersFor(user);
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
		System.out.println("Upcoming orders:");
		for (Order o : upcomingOrders)
			System.out.println("(" + (index++) + ") order "
					+ o.toString().substring(0, 
					((o.toString().length() >= 25?25:o.toString().length()))		
					) + "...");
		System.out.println("Finished orders:");
		for (Order o : prevOrders)
			System.out.println("(" + (index++) + ") " + o.toString().substring(0, 
					((o.toString().length() >= 25?25:o.toString().length()))		
					) + "...");
	}

	private int presentMenu() {
		System.out
				.println("Do you want to:\n(1) Order a car\n(2) View Order details\n(3) Log out");
		return Integer.parseInt(LineReader.readLine());
	}

	private void orderCar() {
		
		int isTruck = 0;
		
		System.out.println("Do you wish to order a \n(1) Car\n(2) Truck");
		
		isTruck = LineReader.readInt();
		
		if(isTruck==1)
		{
			isTruck = 0;
			System.out.println("What type of car do you want to order?");
			System.out.println("(1) Model A\n(2) Model B\n(3) Model C");
		}
		else
		{
			isTruck = 3;
			System.out.println("What type of truck do you want to order?");
			System.out.println("(1) Model X\n(2) Model Y");
		}

		int choice = Integer.parseInt(LineReader.readLine());

		OrderBuilder spec = this.getSpecification(choice);

		setBody(spec);
		setColor(spec);
		setEngine(spec);
		setGearbox(spec);
		setSeats(spec);
		setAirco(spec);
		setWheels(spec);
		setSpoiler(spec);

		System.out.println("Are you sure you want to place this order:");

		if (LineReader.readLine().toLowerCase().startsWith("n")) {
			System.out.println("Your order has been cancelled");
			return;
		}
		
		int expDelivery = orderController.placeOrder(spec);
		
		System.out.println("Your order has been placed");
		System.out.println("We expect your order to be ready on: "
				+ expDelivery);

		upcomingOrders = systemController.getScheduledOrdersFor(user);
	}

	private OrderBuilder getSpecification(int n) {
		OrderBuilder spec = null;
		switch(n)
		{
		case 1: spec = new ModelA();break;
		case 2: spec = new ModelB();break;
		case 3: spec = new ModelC();break;
		case 4: spec = new ModelX();break;
		case 5: spec = new ModelY();break;
		default: throw new IllegalArgumentException("model type does not exist");
		}
		return spec;
	}

	private void displayOrderDetails() {
		System.out.println("For what order do you want to display details?:");

		int order = LineReader.readInt();

		if (order > 0 && order <= upcomingOrders.size()) {
			System.out.println(upcomingOrders.get(order - 1));
		} else if (order > upcomingOrders.size()
				&& order <= upcomingOrders.size() + prevOrders.size() + 1) {
			System.out.println(prevOrders.get(order - 1
					- upcomingOrders.size()));
		}
	}
}
