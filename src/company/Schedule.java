package company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.TimeStamp;
import car.CarOrder;

public class Schedule {

	private static final int START_HOUR = 6;
	private static final int END_HOUR = 22;
	private static final int BUILD_TIME = 3*60;

	private TimeStamp currentTime;
	private TimeStamp endTime;
	private LinkedList<CarOrder> schedule;
	private CarOrder[] inAssembly;
	private boolean dayHasEnded;

	public Schedule() {
		schedule = new LinkedList<CarOrder>();
		currentTime = new TimeStamp(0, START_HOUR, 0);
		endTime = new TimeStamp(0,END_HOUR,0);
		inAssembly = new CarOrder[3];
		dayHasEnded = false;
	}

	/**
	 * @param minutes
	 * 		time the last cycle took
	 * @return
	 * 		next car to be assembled on assembly line;
	 * 		null if no next car is scheduled for the current day
	 * 		or if there are no new carorders
	 */
	public CarOrder next(int minutes) {
		if(dayHasEnded) {
			throw new IllegalArgumentException("proceed to next day first!");
		}
		if(minutes < 1 || minutes > 120) {
			throw new IllegalArgumentException("invalid amount of minutes");
		}
		CarOrder result;
		currentTime = currentTime.increase(minutes);
		if(inAssembly[0] != null) {
			inAssembly[0].setFinished(currentTime);
		}
		inAssembly[0] = inAssembly[1];
		inAssembly[1] = inAssembly[2];

		CarOrder next = schedule.peek();
		TimeStamp completion = currentTime.increase(BUILD_TIME);

		if(completion.isBefore(endTime)) {
			inAssembly[2] = next;
			result = next;
			schedule.remove();
		} else {
			inAssembly[2] = null;
			result = null;
		}

		if(assemblyIsEmpty() && !currentTime.increase(BUILD_TIME).isBefore(endTime)) {
			dayHasEnded = true;
		}

		updateCompletionTimes();
		return result;
	}

	/**
	 * starts new day
	 * 
	 * @return
	 * 		The first carOrder for the new day or null if there are no
	 * 		carOrders
	 */
	public CarOrder newDay() {
		if(!dayHasEnded) {
			throw new IllegalArgumentException("Cannot advance to new day yet");
		}
		if(currentTime.DAY == endTime.DAY) {
			currentTime = new TimeStamp(currentTime.DAY+1, START_HOUR, 0);
		} else {
			currentTime = new TimeStamp(currentTime.DAY, START_HOUR, 0);
		}
		endTime = new TimeStamp(currentTime.DAY, END_HOUR, 0);
		dayHasEnded = false;
		updateCompletionTimes();
		return schedule.poll();
	}

	public void addOrder(CarOrder order) {
		TimeStamp completion;
		if(schedule.isEmpty()) {
			completion = currentTime.increase(BUILD_TIME);
		} else {
			completion = schedule.getLast().getCompletionTime().increase(60);
		}
		if(completion.HOUR > END_HOUR) {
			completion = new TimeStamp(completion.DAY+1, START_HOUR + BUILD_TIME, 0);
		}
		order.setCompletionTime(completion);
		schedule.add(order);
	}

	public boolean dayHasEnded() {
		return dayHasEnded;
	}

	public List<CarOrder> getPendingOrders() {
		List<CarOrder> list = new ArrayList<CarOrder>();
		for (CarOrder order : inAssembly) {
			if(order != null) {
				list.add(order);
			}
		}
		list.addAll(schedule);
		return list;
	}

	private void updateCompletionTimes() {
		for (int i = 0; i < inAssembly.length; i++) {
			if(inAssembly[i] != null) {
				inAssembly[i].setCompletionTime(currentTime.increase((i+1)*60));
			}
		}

		TimeStamp prev = currentTime.increase(BUILD_TIME);
		for (CarOrder order : schedule) {
			TimeStamp next = prev.increase(60);
			if(next.HOUR > END_HOUR) {
				next = new TimeStamp(next.DAY+1, START_HOUR + BUILD_TIME, 0);
			}
			order.setCompletionTime(next); 
			prev = next;
		}
	}

	private boolean assemblyIsEmpty() {
		for (CarOrder order : inAssembly) {
			if(order != null) {
				return false;
			}
		}
		return true;
	}
}
