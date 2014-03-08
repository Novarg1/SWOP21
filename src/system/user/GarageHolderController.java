package system.user;


import java.util.List;

import car.CarOrder;
import company.Company;
import system.functionality.Functionality;
import system.functionality.FunctionalityController;
import system.functionality.FunctionalityControllerFactory;
import system.userinterface.UserInterface;

public class GarageHolderController implements UserController {

	private GarageHolder user;
	private Company company;
	
	public GarageHolderController(GarageHolder user, Company company) {
		this.user = user;
		this.company = company;
	}
	@Override
	public void provideUI(UserInterface ui) {
		// TODO Auto-generated method stub
		
		// first show the garage holder what he has already ordered that is pending
		List<CarOrder> ordersInProgress = this.company.getPendingOrders(this.user);
		for(CarOrder order : ordersInProgress)
			ui.displayString(order.toString());

		// first show the garage holder what he has already ordered that is finished
		List<CarOrder> ordersFinished = this.company.getFinishedOrders(this.user);
		for(CarOrder order : ordersFinished)
			ui.displayString(order.toString());
		
		
		ui.displayString("Welcome to the garage holder menu");
		ui.displayString("");
		ui.displayString("1. Place a new order");
		ui.displayString("");
		String input = ui.displayStringWithInput("Enter your choice:");
		
		if(input.equals("1"))
		{
			FunctionalityController controller = 
					FunctionalityControllerFactory.getControllerFor(Functionality.FUNCTIONALITY_ORDER_PROCESS);
			
			controller.provideFunctionality(ui, user, company);
		}
		
	}
}
