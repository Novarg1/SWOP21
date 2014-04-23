package supportingfiles;

import controllers.SystemController;
import view.ViewSystem;

public class ConveyerBeltProgram {

	public static void main(String[] args) 
	{	
		SystemController systemController = new SystemController();
		
		ViewSystem system = new ViewSystem(systemController);
		
		system.show();
	}
}
