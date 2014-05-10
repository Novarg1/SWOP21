package view;

import util.LineReader;
import util.TimeStamp;
import vehicle.order.CustomSeatsBuilder;
import vehicle.order.OrderBuilder;
import vehicle.order.PaintJobBuilder;
import controllers.SystemController;

public class ViewCustomShop extends ViewOrderForm
{

	public ViewCustomShop(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean show() 
	{
		System.out.println("Do you want to: \n(1) Order a paintjob\n(2) Install custom seats");
		int select = LineReader.readInt();
		switch(select)
		{
		case 1: orderPaintJob(); break;
		case 2: orderSeatsJob(); break;
		default: System.out.println("This option was not presented.");
				 break;
		}
		return true;
	}
	
	private void orderPaintJob()
	{
		OrderBuilder spec = new PaintJobBuilder();
		
		setColor(spec);
		setDeadLine(spec);
		
		orderController.placeOrder(spec);
	}
	
	private void orderSeatsJob()
	{
		OrderBuilder spec = new CustomSeatsBuilder();
		
		setSeats(spec);
		setDeadLine(spec);
		
		orderController.placeOrder(spec);
	}
	
	private void setDeadLine(OrderBuilder spec)
	{
		System.out.println("What deadline do you have in mind?");
		
		TimeStamp t = new TimeStamp(LineReader.readInt()+1,0,0);
		
		spec.setDeadline(t);
	}
}
