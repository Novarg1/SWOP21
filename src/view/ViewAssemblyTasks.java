package view;

import java.util.List;

import company.WorkStation;
import util.LineReader;
import car.parts.Carpart;
import controllers.SystemController;
import controllers.AssemblyController;

public class ViewAssemblyTasks extends View
{
	private AssemblyController assemblyController;
	
	public ViewAssemblyTasks(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
		int workstationId = selectWorkstation();
		WorkStation w = systemController.getWorkstation(workstationId);
		assemblyController = new AssemblyController(w);
	}
	
	@Override
	public boolean show() 
	{
		boolean running = true;
		
		while(running)
		{
			List<Carpart> pendingTasks = assemblyController.getTasksForWorkstation();
			if(pendingTasks.size()==0)
			{
				System.out.println("no pending tasks for this workstation \n press any key to return");
				LineReader.readLine();
				running = false;
			}
			else
			{
				displayTasks(pendingTasks);
				performTask(pendingTasks);
				
				System.out.println("Do you want to select another task?");
				if(LineReader.readLine().toLowerCase().startsWith("n"))
				{
					running = false;
				}
			}
		}
		
		return true;
	}
	
	private void displayTasks(List<Carpart> list)
	{
		System.out.println("Tasks for this workstation:");
		int index = 1;
		for(Carpart p : list)
		{
			System.out.println("(" + (index++) + ") install " + p);
		}
	}
	
	private void performTask(List<Carpart> list)
	{
		System.out.println("Which tasks do you want to perform?");
		int index = LineReader.readInt();
		if(index > 0 && index <= list.size())
		{
			System.out.println("Assembly instructions:\n" +
							list.get(index).getAssemblyInstructions() + 
							"\nHow long the the installation of this part take?");
			int t = LineReader.readInt();
			assemblyController.installPart(list.get(index-1), t);
		}
	}
	
	private int selectWorkstation()
	{
		System.out.println("At which workstation do you want to work?");
		return LineReader.readInt();
	}
}
