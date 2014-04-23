package view;

import util.LineReader;
import util.TimeStamp;
import car.CustomSeatsSpec;
import car.OrderSpecification;
import car.PaintJobSpec;
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
		OrderSpecification spec = new PaintJobSpec();
		
		setColor(spec);
		setDeadLine(spec);
		
		orderController.placeOrder(spec);
	}
	
	private void orderSeatsJob()
	{
		OrderSpecification spec = new CustomSeatsSpec();
		
		setSeats(spec);
		setDeadLine(spec);
		
		orderController.placeOrder(spec);
	}
	
	private void setDeadLine(OrderSpecification spec)
	{
		System.out.println("What deadline do you have in mind?");
		
		TimeStamp t = new TimeStamp(LineReader.readInt()+1,0,0);
		
		spec.setDeadline(t);
	}
}
