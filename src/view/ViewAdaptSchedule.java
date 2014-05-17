package view;

import company.schedule.FIFO;
import company.schedule.SchedulingAlgorithm;
import company.schedule.SpecificationBatch;
import user.User;
import util.LineReader;
import vehicle.order.ModelA;
import vehicle.order.ModelB;
import vehicle.order.ModelC;
import vehicle.order.ModelX;
import vehicle.order.ModelY;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import controllers.SystemController;
import controllers.ScheduleController;

public class ViewAdaptSchedule extends ViewOrderForm {
	ScheduleController scheduleController;

	public ViewAdaptSchedule(SystemController c) {
		super(c);
		scheduleController = new ScheduleController(
				systemController.getScheduler());
	}

	@Override
	public boolean show() {
		System.out.print("The current selected scheduling algorithm is \"");
		System.out.println(scheduleController.getCurrentAlgorithm() + "\"");

		System.out.println("Do you want to change this to");

		System.out.println("(1) FIFO\n(2) SpecificationBatch");

		int index = LineReader.readInt();

		if (index == 1)
			scheduleController.setAlgorithm(getFIFO());
		else
			scheduleController.setAlgorithm(getSpecificationBatch());
		return true;
	}

	private SchedulingAlgorithm getFIFO() {
		return new FIFO();
	}

	private SchedulingAlgorithm getSpecificationBatch() {
		System.out
				.println("For which specification do you want to set the batch processor");

		System.out.println("(1) Cars\n(2) Trucks");

		int choice1 = LineReader.readInt();

		if (choice1 == 1) {
			choice1 = 0;
			System.out.println("For which model type?:\n(1) A\n(2) B\n(3) C");
		} else {
			choice1 = 3;
			System.out.println("For which model type?:\n(1) X\n(2) Y");
		}
		int choice2 = LineReader.readInt();

		OrderBuilder builder = getSpecification(choice1 + choice2);

		setBody(builder);
		setColor(builder);
		setEngine(builder);
		setGearbox(builder);
		setSeats(builder);
		setAirco(builder);
		setWheels(builder);
		setSpoiler(builder);
		
		 return new SpecificationBatch(builder.extractOrder());
	}

	private OrderBuilder getSpecification(int n) {
		OrderBuilder spec = null;
		switch (n) {
		case 1:
			spec = new ModelA();
			break;
		case 2:
			spec = new ModelB();
			break;
		case 3:
			spec = new ModelC();
			break;
		case 4:
			spec = new ModelX();
			break;
		case 5:
			spec = new ModelY();
			break;
		default:
			throw new IllegalArgumentException("model type does not exist");
		}
		return spec;
	}
}
