package view;

import util.LineReader;
import controllers.AssemblyLineController;
import controllers.SystemController;

/**
 * Provides a UI to manage the status of different assembly lines
 * @author jonathanlangens
 *
 */
public class ViewOperationalStatus extends View
{
	private AssemblyLineController assemblyController;
	
	protected ViewOperationalStatus(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
		this.initAssemblyLineController();
	}

	@Override
	public boolean show() {
		// TODO Auto-generated method stub
		System.out.println("Assembly Line's operational status is:"+
							assemblyController.getStatus());
		
		System.out.println("Do you wish to change this to:(1)OP1\n(2)OP2");
		int choice = LineReader.readInt();
		switch(choice)
		{
		default:break;
		}
		
		return true;
	}

	private void initAssemblyLineController()
	{
		System.out.println("For which assembly line do you want to check the OS?");
		int choice = LineReader.readInt();
		this.assemblyController = new AssemblyLineController(systemController.getAssemblyLine(choice));
	}
}
