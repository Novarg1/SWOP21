package company;

import java.util.ArrayList;
import java.util.List;

import system.user.GarageHolder;
import car.CarModel;
import car.CarOrder;
import car.StandardModel;

public class Company {

	private Schedule schedule;
	private AssemblyLine assemblyLine;
	private List<CarModel> carModels;
	private List<CarOrder> finishedOrders;
	
	public Company() {
		schedule = new Schedule();
		assemblyLine = new AssemblyLine(new WorkStation[] {
				new CarBodyPost(),
				new DriveTrainPost(),
				new AccesoiresPost()
		});
		carModels = new ArrayList<CarModel>();
		carModels.add(new StandardModel());
		finishedOrders = new ArrayList<CarOrder>();
	}

	/**
	 * @return the carModels
	 */
	public List<CarModel> getCarModels() {
		return carModels;
	}
	
	/**
	 * places new order
	 */
	public void placeOrder(CarOrder order) {
		schedule.addOrder(order);
	}
	
	/**
	 * advances assembly line
	 * 
	 * @param minutes
	 * 		time the last cycle took
	 */
	public void advanceAssemblyLine(int minutes) {
		CarOrder order = schedule.next(minutes);
		finishedOrders.add(assemblyLine.advance(order).ORDER);
	}

	public List<CarOrder> getFinishedOrders(GarageHolder client) {
		List<CarOrder> list = new ArrayList<CarOrder>();
		for (CarOrder order : finishedOrders) {
			if(order.getClient().equals(client)) {
				list.add(order);
			}
		}
		return null;
	}
	
	public List<CarOrder> getPendingOrders(GarageHolder client) {
		//TODO
		return null;
	}
}
