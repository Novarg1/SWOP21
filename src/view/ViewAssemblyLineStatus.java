package view;

import java.util.List;

import company.WorkStation;

import controllers.SystemController;

public class ViewAssemblyLineStatus extends View
{

	public ViewAssemblyLineStatus(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean show() 
	{
		System.out.println("The current status of the assembly line is:");
		List<WorkStation> list = systemController.getWorkstations();
		for(WorkStation w : list)
		{
			System.out.println("> Workstation " + w.getId() + " status:");
			System.out.println(w.getPendingTasks());
		}
		return true;
	}

}
