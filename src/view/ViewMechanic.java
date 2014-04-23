package view;

import util.LineReader;
import controllers.SystemController;
import controllers.AssemblyController;

public class ViewMechanic extends View
{

	public ViewMechanic(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean show() {
		System.out.println("What do you want to do?\n(1) perform assembly tasks"
				+ "\n(2) check assembly line status");
		switch(LineReader.readInt())
		{
		case 1: new ViewAssemblyTasks(systemController);break;
		case 2: new ViewAssemblyLineStatus(systemController);break;
		}
		return true;
	}

}
