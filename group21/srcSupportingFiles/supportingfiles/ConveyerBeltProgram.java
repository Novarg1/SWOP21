package supportingfiles;

import controllers.SystemController;
import view.ViewMain;

public class ConveyerBeltProgram {

	public static void main(String[] args) 
	{	
		SystemController systemController = new SystemController();
		
		ViewMain system = new ViewMain(systemController);
		
		system.show();
	}
}
