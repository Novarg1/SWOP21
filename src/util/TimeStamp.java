package util;

public class TimeStamp implements Comparable<TimeStamp> {

	public final int DAY;
	public final int HOUR;
	public final int MINUTE;

	/**
	 * Creates a new Timestamp with given day, hour and minute
	 * @param day
	 * @param hour
	 * @param minute
	 */
	public TimeStamp(int day, int hour, int minute) {
		if(day < 0 || hour < 0 || hour > 23 || minute < 0 || minute > 59) {
			throw new IllegalArgumentException("invalid timeStamp");
		}
		DAY = day;
		HOUR = hour;
		MINUTE = minute;
	}

	/**
	 * returns a new timestamp that is given time (minutes) past 
	 * this timestamp
	 */
	public TimeStamp increase(int minutes) {
		int nMinute = MINUTE + minutes;
		int nHour = HOUR;
		int nDay = DAY;

		if(nMinute >= 60) {
			nHour += 1;
			nMinute %= 60;
			if(nHour >= 24) {
				nDay += 1;
				nHour %= 24;
			}
		}
		return new TimeStamp(nDay, nHour, nMinute);
	}

	@Override
	public int compareTo(TimeStamp other) {
		if ((this.DAY < other.DAY) || (this.DAY == other.DAY && this.HOUR < other.HOUR)
				|| (this.DAY == other.DAY && this.HOUR == other.HOUR && this.MINUTE < other.MINUTE)) {
			return -1;
		}

		if (this.DAY == other.DAY && this.HOUR == other.HOUR && this.MINUTE == other.MINUTE) {
			return 0;
		}
		return 1;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + DAY;
		result = prime * result + HOUR;
		result = prime * result + MINUTE;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeStamp other = (TimeStamp) obj;
		if (DAY != other.DAY)
			return false;
		if (HOUR != other.HOUR)
			return false;
		if (MINUTE != other.MINUTE)
			return false;
		return true;
	}

}
