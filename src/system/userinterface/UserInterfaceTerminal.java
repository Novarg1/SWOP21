package system.userinterface;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
/*
 * Basic implementation of the UserInterface interface
 * it uses the standard java system methods to communicate
 * with the terminal
 */
public class UserInterfaceTerminal implements UserInterface
{

	@Override
	public void displayString(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);
	}

	@Override
	public String displayStringWithInput(String s) {
		// TODO Auto-generated method stub
		this.displayString(s);
		
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
