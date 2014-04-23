package view;

import java.util.ArrayList;
import java.util.List;

import util.LineReader;
import car.CustomSeatsSpec;
import car.Order;
import car.OrderSpecification;
import car.PaintJobSpec;
import car.parts.Carpart;
import car.parts.Color;
import car.parts.Seats;
import controllers.SystemController;

public class ViewCustomShop extends View
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
		
		System.out.println("What color do you want the carbody to be sprayed in?");
		
		List<Carpart> options = new ArrayList<>(spec.getViableOptions(Color.class));
		
		int index = 1;
		
		for(Carpart p : options)
			System.out.println("(" + (index++) + ") " + p);
		
		index = LineReader.readInt();
		
		spec.add(options.get(index));
		
		systemController.placeOrder(new Order(spec,
							systemController.getLoggedInUser()));
	}
	
	private void orderSeatsJob()
	{
		OrderSpecification spec = new CustomSeatsSpec();
		
		System.out.println("Which seats did you want to have installed?");
		
		List<Carpart> options = new ArrayList<>(spec.getViableOptions(Seats.class));
		
		int index = 1;
		
		for(Carpart p : options)
			System.out.println("(" + (index++) + ") " + p);
		
		index = LineReader.readInt();
		
		spec.add(options.get(index));
		
		systemController.placeOrder(new Order(spec,
				systemController.getLoggedInUser()));
	}
}
