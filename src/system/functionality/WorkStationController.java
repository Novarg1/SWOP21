package system.functionality;

import system.userinterface.UserInterface;

public class WorkStationController implements FunctionalityController
{

	@Override
	public boolean provideFunctionality(UserInterface userInterface) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(
				userInterface.displayStringWithInput("At what workstation are you working?"));
		// get workstation n
		
		// do
		// display work to do
		// select work to do
		// ask to do new job
		// while(new job is yes)
		return false;
	}
}
