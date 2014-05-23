package view;

import java.util.List;

import util.LineReader;
import company.assemblylines.Assemblyline;
import company.assemblylines.Assemblyline.Status;
import company.workstations.Workstation;
import controllers.SystemController;

/**
 * Provide a UI for manipulating and consulting the assembly lines status
 * @author jonathanlangens
 *
 */
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
		
		Assemblyline line = systemController.getAssemblyLine(choice);
		System.out.println(line.getStatus());
		if(line.getStatus() == Status.OPERATIONAL)
		{
			System.out.println("Do you want to change to maintenance or broken?"+
								"\n(1) Maintenance\n(2) Broken");
			choice = LineReader.readInt();
			if(choice == 1)
				line.setStatus(Status.MAINTENANCE, 0);
			else if(choice == 2)
					line.setStatus(Status.BROKEN, 0);
		}
		else 
		{
			if(line.getStatus() == Status.MAINTENANCE)
			{
				System.out.println("Do you want to change to operational?");
				if(LineReader.readLine().toLowerCase().startsWith("y"))
				{
					System.out.println("What amount of time has passed?");
					int time = LineReader.readInt();
					line.setStatus(Status.OPERATIONAL, time);
				}
			}
			else
			{
				System.out.println("Do you want to change to operational?");
				if(LineReader.readLine().toLowerCase().startsWith("y"))
				{
					System.out.println("What amount of time has passed?");
					int time = LineReader.readInt();
					line.setStatus(Status.OPERATIONAL, time);
				}
			}
		}
		return true;
	}

}
