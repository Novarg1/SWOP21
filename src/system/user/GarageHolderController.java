package system.user;

import system.userinterface.UserInterface;

public class GarageHolderController implements UserController {

	public GarageHolderController(String uname)
	{
		this.userName = uname;
	}
	@Override
	public void provideUI(UserInterface ui) {
		// TODO Auto-generated method stub
		
	}

	private String userName;
}
