package system.userinterface;

import java.util.LinkedList;

/**
 * This is the testing oriented version of the userinterface
 * It also expects that the user provides it with the input
 * it will have to give. The strings that are produced by the system
 * are shown with a program: in front of, while the input string are
 * preceded by input:
 * 
 */
public class UserInterfaceUseCaseTester implements UserInterface
{
	public UserInterfaceUseCaseTester()
	{
		this.inputs = new LinkedList<String>();
	}

	@Override
	public void displayString(String s) {
		System.out.println("Program: " + s);	
	}

	@Override
	public String displayStringWithInput(String s) {
		// TODO Auto-generated method stub
		this.displayString(s);
		String input = this.nextInput();
		System.out.println("Input  : " + input);
		return input;
	}
	
	public void addInput(String input)
	{
		this.inputs.add(input);
	}
	
	private String nextInput()
	{
		return this.inputs.pop();
	}

	private LinkedList<String> inputs;
}
