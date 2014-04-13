package supportingfiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import system.SystemController;
import system.user.UserController;
import system.userinterface.UserInterface;
import system.userinterface.UserInterfaceDemo;
import system.userinterface.UserInterfaceTerminal;

public class ConveyerBeltProgram {

	public static void main(String[] args) 
	{
		SystemController s = new SystemController();

		boolean running = true;
		
		while(running)
		{
			System.out.println("Select the user you want to login:"
					+ "\n(1) Manager\n(2) Garage Holder \n(3) Mechanic\n(4) Custom Shop Owner");
			
			int user = Integer.parseInt(readLine());
			
			switch(user)
			{
			case 1: s.logInUser(0);
					break;
			case 2: s.logInUser(1);
					presentGarageHolderMenu();
					break;
			case 3: s.logInUser(2);
					break;
			case 4: s.logInUser(3);
					break;
					
			default:System.out.println("User not known");
					break;
			}
			
			System.out.println("Do you wish to quit?");
			if(!readLine().startsWith("y"))
				running = false;
		}
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
	
	public static String readLine()
	{		
		InputStreamReader sr =new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(sr);

		String input = "";

		try
		{
			input = br.readLine();
		}catch(Exception e){}

		return input;
	}
}
