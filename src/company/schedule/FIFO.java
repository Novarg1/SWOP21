package company.schedule;

import java.util.ArrayList;
import java.util.List;

import vehicle.order.Order;

public class FIFO extends SchedulingAlgorithm {

	public FIFO(List<Order> originalList) {
		super(originalList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void recalculate() {
		sortedOrderList = new ArrayList<Order>();
		for(Order o : orderList)
			sortedOrderList.add(o);
	}


}
