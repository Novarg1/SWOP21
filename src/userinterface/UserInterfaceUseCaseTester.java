package userinterface;

import usecases.UseCase;

public class UserInterfaceUseCaseTester implements UserInterface
{
	public UserInterfaceUseCaseTester(UseCase useCase)
	{
		this.useCase = useCase;
		supressOutput = false;
	}

	@Override
	public void displayString(String s) {
		if(this.supressOutput == false)
			System.out.println("Program: " + s);	
	}

	@Override
	public String displayStringWithInput(String s) {
		// TODO Auto-generated method stub
		this.displayString(s);
		String input = useCase.nextInput();
		if(input.equals(UserInterfaceUseCaseTester.supressOutputString))
		{
			this.supressOutput = !this.supressOutput;
			input = this.displayStringWithInput(s);
		}
		else if(this.supressOutput == false)
		{
			System.out.println("Input  : " + input);
		}
		return input;
	}
	
	public static String supressOutputString = "TOGGLESUPRESSOUTPUT";

	private UseCase useCase;
	private boolean supressOutput;
}
