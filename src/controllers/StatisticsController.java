package controllers;

import java.util.List;

import car.Order;

public class StatisticsController
{
	private SystemController systemController;
	
	private double averageCarsPerDay;
	private int medianCarsPerDay;
	private int carsProducedInLast2Days;
	
	public StatisticsController(SystemController s)
	{
		systemController = s;
		List<Order> finishedOrders = systemController.getAllFinishedOrders();
		int nDays = systemController.getSchedule().getNumberOfOperationalDays();

		averageCarsPerDay = finishedOrders.size()/nDays;
		medianCarsPerDay = (int)(finishedOrders.size()/nDays);
		
		int yesterday = systemController.getSchedule().getCurrentTime().getDay() - 1;
		
		for(Order o : finishedOrders)
		{
			if(o.getCompletionTime().getDay() >= yesterday)
				++carsProducedInLast2Days;
		}
	}
	
	public double getAverageCarsProducedPerDay()
	{
		return averageCarsPerDay;
	}
	
	public int getMedianCarsProducedPerDay()
	{
		return medianCarsPerDay;
	}
	
	public int getNumberCarsProducedLast2Days()
	{
		return carsProducedInLast2Days;
	}
}
