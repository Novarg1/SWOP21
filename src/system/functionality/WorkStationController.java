package system.functionality;

import java.util.List;

import car.CarPart;
import company.WorkStation;
import system.userinterface.UserInterface;

public class WorkStationController implements FunctionalityController {
	private WorkStation workstation;

	public WorkStationController(WorkStation ws) {
		workstation = ws;
	}

	@Override
	public boolean provideFunctionality(UserInterface ui) {
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
