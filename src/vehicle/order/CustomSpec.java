package vehicle.order;

import util.TimeStamp;

public abstract class CustomSpec extends OrderSpecification {

	private TimeStamp deadline = null;
	
	@Override
	public TimeStamp getDeadline() {
		return deadline;
	}
	
	@Override
	public void setDeadline(TimeStamp deadline) {
		this.deadline = deadline;
	}
}
