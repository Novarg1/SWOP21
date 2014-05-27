package view;

import java.util.Set;

import company.workstations.Workstation;
import util.LineReader;
import vehicle.assemblytasks.Task;
import controllers.SystemController;
import controllers.WorkstationController;

/**
 * Provides a UI to display and perform tasks on a workstation
 * @author jonathanlangens
 *
 */
public class ViewAssemblyTasks extends View
{
	private WorkstationController assemblyController;
	
	public ViewAssemblyTasks(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
		assemblyController = new WorkstationController(selectWorkstation());
	}
	
	@Override
	public boolean show() 
	{
		boolean running = true;
		
		while(running)
		{
			Set<Task> pendingTasks = assemblyController.getTasksForWorkstation();
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
	
	private void displayTasks(Set<Task> pendingTasks)
	{
		System.out.println("Tasks for this workstation:");
		int index = 1;
		for(Task p : pendingTasks)
		{
			System.out.println("(" + (index++) + ") install " + p);
		}
	}
	
	private void performTask(Set<Task> pTasks)
	{
		Task[] pendingTasks = (Task[]) pTasks.toArray();
		System.out.println("Which tasks do you want to perform?");
		int index = LineReader.readInt();
		if(index > 0 && index <= pendingTasks.length)
		{
			System.out.println("Assembly instructions:\n" +
							//pendingTasks[index].getAssemblyInstructions() + 
							"\nHow long the the installation of this part take?");
			int t = LineReader.readInt();
			assemblyController.perform(pendingTasks[index-1], t);
		}
	}
	
	private Workstation selectWorkstation()
	{
		System.out.println("At which assembly line would you want to perform work?");
		int choice = LineReader.readInt();
		System.out.println("At which workstation do you want to work?");
		int wsChoice = LineReader.readInt();
		return systemController.selectWorkstationWithId(choice, wsChoice);
	}
}
