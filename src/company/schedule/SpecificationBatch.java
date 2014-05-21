package company.schedule;

import java.util.ArrayList;
import java.util.List;

import vehicle.order.Order;

public class SpecificationBatch extends SchedulingAlgorithm {

	private Order batchSample = null;

	public SpecificationBatch(List<Order> originalList) {
		super(originalList);
		// TODO Auto-generated constructor stub
	}
	
	public void setSample(Order o)
	{
		batchSample = o;
	}

	@Override
	public void recalculate() 
	{
		List<Order> temp = cloneList(orderList);
		sortedOrderList = new ArrayList<Order>();
		
		for(Order o : temp)
			if(isOfBatch(o) == true)
			{
				temp.remove(o);
				sortedOrderList.add(o);
			}
		
		for(Order o : temp)
			sortedOrderList.add(o);
	}
	
	private boolean isOfBatch(Order o)
	{
		if(batchSample == null)
			return false;
		return batchSample.matches(o);
	}

	private List<Order> cloneList(List<Order> list)
	{
		List<Order> toreturn = new ArrayList<Order>();
		for(Order o:list)
			toreturn.add(o);
		return toreturn;
	}
}
