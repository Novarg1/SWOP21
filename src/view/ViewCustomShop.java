package view;

import util.LineReader;
import util.Timestamp;
import vehicle.order.CustomOrderBuilder;
import vehicle.order.CustomSeats;
import vehicle.order.PaintJob;
import controllers.SystemController;
/**
 * Provides a UI that lets a customshop holder place and manage placed
 * custom orders
 * @author jonathanlangens
 *
 */
public class ViewCustomShop extends ViewOrderForm {

	public ViewCustomShop(SystemController c) {
		super(c);
	}

	@Override
	public boolean show() {
		System.out
				.println("Do you want to: \n(1) Order a paintjob\n(2) Install custom seats");
		int select = LineReader.readInt();
		switch (select) {
		case 1:
			orderPaintJob();
			break;
		case 2:
			orderSeatsJob();
			break;
		default:
			System.out.println("This option was not presented.");
			break;
		}
		return true;
	}

	private void orderPaintJob() {
		CustomOrderBuilder spec = new PaintJob();

		setColor(spec);
		setDeadLine(spec);

		orderController.placeOrder(spec);
	}

	private void orderSeatsJob() {
		CustomOrderBuilder spec = new CustomSeats();

		setSeats(spec);
		setDeadLine(spec);

		orderController.placeOrder(spec);
	}

	private void setDeadLine(CustomOrderBuilder spec) {
		System.out.println("What deadline do you have in mind?");
		spec.setDeadline(Timestamp.beginningOfDay(LineReader.readInt()));
	}
}
