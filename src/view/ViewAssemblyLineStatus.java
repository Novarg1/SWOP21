package view;

import java.util.List;

import util.LineReader;
import company.workstations.Workstation;
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
		System.out.println("For what assembly line do you want to check the status?");
		int choice = LineReader.readInt();
		System.out.println("The current status of the assembly line is:");
		List<Workstation> list = systemController.getWorkstationsForAssemblyLine(choice);
		for(Workstation w : list)
		{
			System.out.println("> Workstation " + w.toString() + " status:");
			System.out.println(w.getPendingTasks());
		}
		return true;
	}

}
