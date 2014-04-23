package controllers;

public class StatisticsController implements UseCaseController
{
	private SystemController systemController;
	public StatisticsController(SystemController s)
	{
		systemController = s;
	}
}
