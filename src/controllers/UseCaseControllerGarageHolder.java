package controllers;

import java.util.ArrayList;
import java.util.List;

import user.User;
import util.LineReader;
import car.OrderSpecification;
import car.Order;
import car.models.ModelA;
import car.parts.Airco;
import car.parts.Body;
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
					+ o.SPECIFICATION.getType());
		System.out.println("Finished orders:");
		for (Order o : prevOrders)
			System.out.println("(" + (index++) + ") " + o);
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

		Order order = new Order(user, spec);

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
		return new ModelA();
	}

	private void setBody(OrderSpecification spec) {
		while(!spec.bodyChosen()) {
			System.out.println("What body type would you like?");

			List<Body> options = new ArrayList<>(spec.getViableBodies());
			int index = 1;
			for (Body b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setBody(options.get(choice - 1));
			}
		}
	}

	private void setColor(OrderSpecification spec) {
		while(!spec.colorChosen()) {
			System.out.println("What color type would you like?");

			List<Color> options = new ArrayList<>(spec.getViableColors());

			int index = 1;
			for (Color b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setColor(options.get(choice - 1));
			}
		}
	}

	private void setEngine(OrderSpecification spec) {
		while (!spec.engineChosen()) {
			System.out.println("What engine type would you like?");

			List<Engine> options = new ArrayList<>(spec.getViableEngines());

			int index = 1;
			for (Engine b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setEngine(options.get(choice - 1));
			}
		}
	}

	private void setGearbox(OrderSpecification spec) {
		while (!spec.gearboxChosen()) {
			System.out.println("What Gearbox type would you like?");

			List<Gearbox> options = new ArrayList<>(spec.getViableGearboxes());

			int index = 1;
			for (Gearbox b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setGearbox(options.get(choice - 1));
			}
		}
	}

	private void setSeats(OrderSpecification spec) {
		while (!spec.seatsChosen()) {
			System.out.println("What Seats type would you like?");

			List<Seats> options = new ArrayList<>(spec.getViableSeats());

			int index = 1;
			for (Seats b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setSeats(options.get(choice - 1));
			}
		}
	}

	private void setAirco(OrderSpecification spec) {
		if (spec.getViableAircos().isEmpty()) {
			return;
		}

		while (!spec.aircoChosen()) {
			System.out.println("Do you want an airco system? (y/n)");
			if (LineReader.readLine().toLowerCase().startsWith("n")) {
				return;
			}
			System.out.println("What Airco type would you like?");

			List<Airco> options = new ArrayList<>(spec.getViableAircos());

			int index = 1;
			for (Airco b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setAirco(options.get(choice - 1));
			}
		}
	}

	private void setWheels(OrderSpecification spec) {
		while (!spec.wheelsChosen()) {
			System.out.println("What Wheels type would you like?");

			List<Wheels> options = new ArrayList<>(spec.getViableWheels());

			int index = 1;
			for (Wheels b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setWheels(options.get(choice - 1));
			}
		}
	}

	private void setSpoiler(OrderSpecification spec) {
		if (spec.getViableSpoilers().isEmpty()) {
			return;
		}

		while (!spec.spoilerChosen()) {
			System.out.println("Do you want an Spoiler system? (y/n)");
			if (LineReader.readLine().toLowerCase().startsWith("n")) {
				return;
			}
			System.out.println("What Spoiler type would you like?");

			List<Spoiler> options = new ArrayList<>(spec.getViableSpoilers());

			int index = 1;
			for (Spoiler b : options)
				System.out.println("(" + (index++) + ") " + b);

			int choice = LineReader.readInt();

			if (choice > 0 && choice <= options.size()) {
				spec.setSpoiler(options.get(choice - 1));
			}
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
