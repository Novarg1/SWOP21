package supportingfiles;

import controller.Controller;
import session.UserSession;
import usecases.UseCaseX;
import userinterface.UserInterface;
import userinterface.UserInterfaceTerminal;
import userinterface.UserInterfaceUseCaseTester;

public class ConveyerBeltProgram {

	public static void main(String[] args) 
	{
		boolean testing = true;
		
		UserInterface f = (testing? new UserInterfaceUseCaseTester(new UseCaseX()):
								 new UserInterfaceTerminal());
		
		Controller c = new Controller(f);
		
		c.displayWelcomeMessage();
		
		UserSession s = null;
		try
		{
			while((s = c.displayLogin()) != null)
			{
				s.runSession(f);
			}
		}
		catch(Exception e)
		{
		}
		c.displayGoodByeMessage();
	}

}
