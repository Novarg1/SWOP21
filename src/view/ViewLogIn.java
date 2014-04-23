package view;

import controllers.SystemController;
import util.LineReader;

public class ViewLogIn extends View
{
	public ViewLogIn(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean show() 
	{		
		System.out.println("Select the user you want to login:"
			+ "\n(1) Manager\n(2) Garage Holder \n(3) Mechanic\n(4) Custom Shop Owner");
	
		int user = LineReader.readInt();
	
		switch(user)
		{
		case 1: systemController.logInUser(0);
				break;
		case 2: systemController.logInUser(1);
				break;
		case 3: systemController.logInUser(2);
				break;
		case 4: systemController.logInUser(3);
				break;
		default:System.out.println("User not known");
				break;
		}
		return true;
	}

}
