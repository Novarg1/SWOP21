package dao;

import java.util.ArrayList;
import java.util.List;

import user.User;
import user.UserManager;
import util.Timestamp;
import vehicle.order.CustomSeats;
import vehicle.order.ModelA;
import vehicle.order.ModelB;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.order.PaintJob;
import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Spoiler;
import vehicle.parts.Wheels;

/*
 * TODO 
 * * Kijk goed na dat alle parts ondersteund worden door de orderbuilder
 * 
 * * spec.setClient() MOET aangeroepen worden, anders is ze niet valid.
 * 
 * * Als orders finished zijn moet ge ook setCompletionTime() erop aanroepen, anders
 * zijn ze pending.
 * 
 */
/**
 * 
 * Stub implementation for the Order DAO loads fake orders from fake storage
 */
public class OrderDAOImpl implements OrderDAO {

	@Override
	public List<Order> getAllFinishedOrders() {
		List<Order> orders = new ArrayList<Order>();

		UserManager users = new UserManager();

		// first add some finished orders for the garageholder
		User user = users.logInUser(1);

		OrderBuilder spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);
		Order o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_BLACK);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);
		
		spec = new ModelA();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.BREAK);
		spec.add(Color.BLACK);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.RED);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.WINTER);
		spec.add(Airco.MANUAL);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.PERFORMANCE_25DL_V6);
		spec.add(Gearbox.AUTOMATIC_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.setClient(user);
		o = spec.extractOrder();
		o.setFinished(Timestamp.beginningOfDay(1));

		orders.add(o);

		return orders;
	}

	@Override
	public List<Order> getAllPendingOrders() {
		List<Order> orders = new ArrayList<Order>();

		UserManager users = new UserManager();

		// the first three should be custom shop orders
		User user = users.logInUser(3);
		OrderBuilder spec = new PaintJob();
		spec.add(Color.BLUE);
		spec.setClient(user);
		orders.add(spec.extractOrder());

		spec = new PaintJob();
		spec.add(Color.GREEN);
		spec.setClient(user);
		orders.add(spec.extractOrder());

		spec = new CustomSeats();
		spec.add(Seats.VINYL_GREY);
		spec.setClient(user);
		orders.add(spec.extractOrder());

		// then 3 different batch orders
		user = users.logInUser(1);

		spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);

		orders.add(spec.extractOrder());

		spec = new ModelA();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);

		orders.add(spec.extractOrder());

		spec = new ModelA();
		spec.add(Body.BREAK);
		spec.add(Color.BLACK);
		spec.add(Engine.STANDARD_2L_V4);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_BLACK);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		spec.setClient(user);

		orders.add(spec.extractOrder());

		for (int i = 0; i < 3; ++i) {
			spec = new ModelB();
			spec.add(Body.SPORT);
			spec.add(Color.YELLOW);
			spec.add(Engine.PERFORMANCE_25DL_V6);
			spec.add(Gearbox.MANUAL_6);
			spec.add(Seats.LEATHER_BLACK);
			spec.add(Wheels.COMFORT);
			spec.add(Spoiler.LOW);
			spec.setClient(user);

			orders.add(spec.extractOrder());

		}

		return orders;
	}

	@Override
	public int getDay() {
		return 2;
	}

}
