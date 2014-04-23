package view;

import controllers.StatisticsController;
import controllers.SystemController;

public class ViewStatistics extends View
{
	private StatisticsController statistics;
	
	public ViewStatistics(SystemController c) {
		super(c);
		statistics = new StatisticsController(systemController);
	}

	public boolean show()
	{
		return true;
	}
}
