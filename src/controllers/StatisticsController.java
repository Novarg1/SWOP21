package controllers;

public class StatisticsController
{
	private SystemController systemController;
	
	private double averageCarsPerDAy;
	private int medianCarsPerDay;
	private int carsProducedInLast2Days;
	
	public StatisticsController(SystemController s)
	{
		systemController = s;
	}
	
	public double getAverageCarsProducedPerDay()
	{
		return averageCarsPerDAy;
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
