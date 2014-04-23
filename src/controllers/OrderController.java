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

public class OrderController
{
	private SystemController systemController;
	
	public OrderController(SystemController s)
	{
		systemController = s;
	}
	
	public int placeOrder(OrderSpecification specification)
	{
		Order order = new Order(specification,
								systemController.getLoggedInUser());
		systemController.getSchedule().placeOrder(order);
		
		return getExpectedDeliveryDayFor(order);
	}
	
	public int getExpectedDeliveryDayFor(Order order)
	{
		return systemController.getSchedule().getETA(order).getDay();
	}
}