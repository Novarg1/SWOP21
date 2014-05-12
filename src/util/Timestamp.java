package util;

/**
 * a simple class to manage our days. It does not represent a calenderday but
 * rather a working day with a worktime, which is the standard worktime
 * diminished by the overtime of the day before
 */
public class Timestamp implements Comparable<Timestamp> {

	private final int day;
	private final int time;
	private final int overtime;
	private static final int STANDARD_WORKTIME = 17 * 60;
	private static final int MAX_OVERTIME = 8 * 60;

	/**
	 * creates a new timestamp with given daynumber and worktime, adjusted with
	 * the given overtime. If the overtime exceeds the maximum overtime, this
	 * maximum overtime will be used instead.
	 * 
	 * @param day
	 *            number of the day.
	 * @param overtime
	 *            overtime of the day before.
	 */
	private Timestamp(int day, int time, int overtime) {
		if (day < 1 || time < 0 || overtime < 0) {
			throw new IllegalArgumentException("invalid timestamp");
		}
		if (overtime > MAX_OVERTIME) {
			overtime = MAX_OVERTIME;
		}
		this.day = day;
		this.time = time;
		this.overtime = overtime;
	}

	/**
	 * @return new timestamp at beginning of the given day. The day has the
	 *         standard worktime.
	 */
	public static Timestamp beginningOfDay(int day) {
		return new Timestamp(day, 0, 0);
	}

	public int getWorkTime() {
		return STANDARD_WORKTIME - overtime;
	}

	/**
	 * This function initializes the next day based on the current day it
	 * automatically passes the time that was worked too much today
	 * 
	 * @return timestamp at the beginning of the next Day
	 */
	public Timestamp getNextDay() {
		int nextOverTime = (time > getWorkTime()) ? time - getWorkTime() : 0;
		return new Timestamp(this.day + 1, 0, nextOverTime);
	}

	/**
	 * inspector for the day variable
	 * 
	 * @return the daynumber of this timestamp
	 */
	public int getDay() {
		return day;
	}

	/**
	 * inspector for the time variable
	 * 
	 * @return time in minutes
	 */
	public int getTime() {
		return time;
	}

	/**
	 * inspector for the overtime
	 * 
	 * @return overtime of the day before
	 */
	public int getOverTime() {
		return overtime;
	}

	/**
	 * inspector that calculates that the day should be finished if we add
	 * additional time to this day
	 * 
	 * @param additionalTime
	 * @return true if current time + additionalTime >= today's worktime
	 */
	public boolean shouldBeFinishedAfter(int additionalTime) {
		return (time + additionalTime) >= getWorkTime();
	}

	/**
	 * inspector that calculates if the day should be finished base on the
	 * current time
	 * 
	 * @return true if current time >= today's worktime
	 */
	public boolean shouldBeFinished() {
		return this.shouldBeFinishedAfter(0);
	}

	/**
	 * mutator that increases the time with the passed amount
	 * 
	 * @param timeToIncrease
	 */
	public Timestamp increaseTime(int timeToIncrease) {
		return new Timestamp(day, time + timeToIncrease, overtime);
	}

	@Override
	public int compareTo(Timestamp other) {
		if (this.day < other.day
				|| (this.day == other.day && this.time < other.time)) {
			return -1;
		}
		if (this.day == other.day && this.time == other.time) {
			return 0;
		}
		return 1;
	}

	public int getNumberOfOperationalDays() {
		return this.day;
	}
}
