package dao;

import java.util.ArrayList;
import java.util.List;

import user.User;
import user.UserManager;
import vehicle.order.ModelASpec;
import vehicle.order.Order;
import vehicle.order.OrderSpecification;
import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Wheels;

/**
 * Stub implementation for the Order DAO
 * loads fake orders from fake storage
 * @author jonathanlangens
 *
 */

public class OrderDAOImpl implements OrderDAO
{
	@Override
	public List<Order> getAllFinishedOrders() {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		
		UserManager users = new UserManager();
		
		// first add some finished orders for the garageholder
		User user = users.logInUser(2);
		
		OrderSpecification spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_BLACK);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.BREAK);
		spec.add(Color.BLACK);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.RED);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.WINTER);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.PERFORMANCE_25DL_V6);
		spec.add(Gearbox.AUTOMATIC);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		
		orders.add(new Order(spec, user));
		
		return orders;
	}

	@Override
	public List<Order> getAllPendingOrders() {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		
		UserManager users = new UserManager();
		
		// first add some finished orders for the garageholder
		User user = users.logInUser(2);
		
		OrderSpecification spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.BREAK);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		spec = new ModelASpec();
		spec.add(Body.BREAK);
		spec.add(Color.BLACK);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		
		orders.add(new Order(spec, user));
		
		return orders;
	}

}
