package controllers;

import java.util.List;

import vehicle.order.Order;

public class StatisticsController
{
	private SystemController systemController;
	
	private double averageCarsPerDay;
	private int medianCarsPerDay;
	private int carsProducedInLast2Days;
	
	/**
	 * takes a system controller as parameter and uses this to compute
	 * all statistics. If things change after the creation of this
	 * controller it has to be reinitialised
	 * @param s
	 */
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
	
	/**
	 * inspector for avarageCarsPerDay
	 * @return this.avarageCarsPerDay
	 */
	public double getAverageCarsProducedPerDay()
	{
		return averageCarsPerDay;
	}
	
	/**
	 * inspector for medianCarsPerDay
	 * @return this.medianCarsPerDay
	 */
	public int getMedianCarsProducedPerDay()
	{
		return medianCarsPerDay;
	}
	
	/**
	 * inspector for carsProducedinLast2Days
	 * @return this.carsProducedinLast2Days
	 */
	public int getNumberCarsProducedLast2Days()
	{
		return carsProducedInLast2Days;
	}
}
