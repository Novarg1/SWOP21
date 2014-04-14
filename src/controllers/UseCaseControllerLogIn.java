package controllers;

import util.LineReader;

public class UseCaseControllerLogIn implements UseCaseController
{
	@Override
	public boolean guideUseCase(SystemController c) 
	{
		// TODO Auto-generated method stub

		System.out.println("Select the user you want to login:"
				+ "\n(1) Manager\n(2) Garage Holder \n(3) Mechanic\n(4) Custom Shop Owner");
		
		int user = Integer.parseInt(LineReader.readLine());
		
		switch(user)
		{
		case 1: c.logInUser(0);
				break;
		case 2: c.logInUser(1);
				presentGarageHolderMenu();
				break;
		case 3: c.logInUser(2);
				break;
		case 4: c.logInUser(3);
				break;
				
		default:System.out.println("User not known");
				return false;
		}
		return true;
	}

	public static void presentGarageHolderMenu()
	{
		// print history overview
		
		// show option menu
		// 1. order car
		// 2. view order details
		// 3. exit
		
		// process choice
	}
}
