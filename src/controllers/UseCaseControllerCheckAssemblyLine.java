package controllers;

import java.util.List;

import company.WorkStation;

public class UseCaseControllerCheckAssemblyLine implements UseCaseController
{
	public boolean guideUseCase(SystemController c)
	{
		System.out.println("The current status of the assembly line is:");
		List<WorkStation> list = c.getWorkstations();
		for(WorkStation w : list)
		{
			System.out.println("> Workstation " + w.getId() + " status:");
			System.out.println(w.getPendingTasks());
		}
		return true;
	}
}
