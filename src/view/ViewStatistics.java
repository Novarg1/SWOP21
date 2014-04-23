package view;

import controllers.StatisticsController;
import controllers.SystemController;

public class ViewStatistics extends View
{
	private StatisticsController statisticsController;
	
	public ViewStatistics(SystemController c) {
		super(c);
		statisticsController = new StatisticsController(systemController);
	}

	public boolean show()
	{
		System.out.println("The average number of cars produced per day is " +
				statisticsController.getAverageCarsProducedPerDay());
		System.out.println("The median number of cars produced per day is " +
				statisticsController.getMedianCarsProducedPerDay());
		System.out.println("The number of cars produced during the last 2 days is " +
				statisticsController.getNumberCarsProducedLast2Days());
		return true;
	}
}
