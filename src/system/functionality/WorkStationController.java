package system.functionality;

import java.util.List;

import car.CarPart;
import company.Company;
import company.WorkStation;
import system.user.User;
import system.userinterface.UserInterface;

public class WorkStationController implements FunctionalityController {

	public WorkStationController() {
	}

	/*
	 * guides the user through using a workstation
	 * (non-Javadoc)
	 * @see system.functionality.FunctionalityController#provideFunctionality(system.userinterface.UserInterface, system.user.User, company.Company)
	 * @return true if the workstation was successfully used
	 */
	@Override
	public boolean provideFunctionality(UserInterface ui, User user, Company company) {
		int nw = Integer.parseInt(
				ui.displayStringWithInput("At which workstation would you be working?")
				);
		WorkStation workstation = company.getAssemblyLine().getWorkstations()[nw];
		
		while(true) {
			List<CarPart> pending = workstation.getPendingTasks();
			if(pending.isEmpty()) {
				ui.displayStringWithInput("No pending tasks");
			} else {
				CarPart part = null;
				do {
					ui.displayString("Pending tasks:");
					for (int i = 0; i < pending.size(); i++) {
						ui.displayString("(" + (i + 1) + ") " + pending.get(i));
					}
					try {
						int n = Integer.parseInt(
								ui.displayStringWithInput("Which assembly task will you finish next"));
						part = pending.get(n-1);
					} catch (NumberFormatException|IndexOutOfBoundsException e) {
						ui.displayString("invalid input");
					}
				} while (part == null);

				ui.displayStringWithInput("You are now working on: " + part +
						"\nEnter any input when task is finished");
				workstation.install(part);
			}
			if(ui.displayStringWithInput("Do you want to quit working?").equals("y")) {
				break;
			}
		}
		return false;
	}
}
