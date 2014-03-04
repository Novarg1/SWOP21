package system.user;

import system.userinterface.UserInterface;

public class MechanicController implements UserController{
	public MechanicController(String uname)
	{
		this.userName = uname;
	}

	@Override
	public void provideUI(UserInterface ui) {
		// TODO Auto-generated method stub
		
	}

	private String userName;
}
