package util;

public class TimeStamp {

	public final int DAY;
	public final int HOUR;
	public final int MINUTE;

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
				nHour = 0;
				nDay += 1;
			}
		}
		return new TimeStamp(nDay, nHour, nMinute);
	}

	public boolean isBefore(TimeStamp other) {
		return ((this.DAY < other.DAY)
				|| (this.DAY == other.DAY && this.HOUR < other.HOUR)
				|| (this.DAY == other.DAY && this.HOUR == other.HOUR && this.MINUTE <= other.MINUTE));
	}
}
