package supportingfiles;

import system.SystemController;
import system.functionality.FunctionalityController;
import system.usecases.UseCaseX;
import system.user.UserController;
import system.userinterface.UserInterface;
import system.userinterface.UserInterfaceTerminal;
import system.userinterface.UserInterfaceUseCaseTester;

public class ConveyerBeltProgram {

	public static void main(String[] args) 
	{
		boolean testing = true;
		
		UserInterface f = (testing? new UserInterfaceUseCaseTester(new UseCaseX()):
								 new UserInterfaceTerminal());
		
		SystemController c = new SystemController(f);
		
		c.displayWelcomeMessage();
		
		UserController u = null;
		try
		{
			while((u = c.displayLogin()) != null)
			{
				u.provideUI(f);
			}
		}
		catch(Exception e)
		{
		}
		c.displayGoodByeMessage();
	}

}
