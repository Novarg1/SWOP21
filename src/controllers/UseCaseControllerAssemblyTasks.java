package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import car.parts.Carpart;
import car.parts.CarpartsSet;
import util.LineReader;
import company.WorkStation;

public class UseCaseControllerAssemblyTasks implements UseCaseController
{
	private SystemController systemController;
	private int workstationId;
	
	@Override
	public boolean guideUseCase(SystemController c) 
	{
		systemController = c;
		
		selectWorkstation();
		
		boolean running = true;
		
		while(running)
		{
			if(systemController.getPendingTasksForWorkstation(workstationId).size()==0)
			{
				System.out.println("no pending tasks for this workstation \n press any key to return");
				LineReader.readLine();
				running = false;
			}
			else
			{
				List<Carpart> list = tasksAsList();
				
				displayTasks(list);
				performTask(list);
				
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
		System.out.println("Assembly instructions:\n" +
							list.get(index).getAssemblyInstructions() + 
							"\nHow long the the installation of this part take?");
		// install the part
	}

	private List<Carpart> tasksAsList()
	{
		List<Carpart> l = new ArrayList<Carpart>();
		Iterator<Carpart> it = systemController.getPendingTasksForWorkstation(workstationId).iterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	}
	private void selectWorkstation()
	{
		System.out.println("At which workstation do you want to work?");
		workstationId = LineReader.readInt();
	}
	
}
