package company;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import util.TimeStamp;
import car.Order;

public class FIFO extends SchedulingAlgorithm {

	@Override
	public SortedMap<TimeStamp, Order> schedule(TimeStamp currentTime,
			List<Order> orders) {
		SortedMap<TimeStamp, Order> result = new TreeMap<>();

		// TODO

		return result;
	}
}
