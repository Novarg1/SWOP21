package company;

/**
 * a simple class to manage our days. It does not represent a calenderday
 * but rather a working day with 17 work hours and an overflow which is the time
 * that was left from the previous working day
 * 
 * @author jonathanlangens
 *
 */
public class Day 
{
	private int day;
	private int time;
	private int overflow;
	
	private final int DAYTIME = 1020;
	
	/**
	 * constructor that takes 2 params, day indicates the daynumber
	 * and overflow how much time was worked to much yesterday
	 * @param day
	 * @param overflow
	 */
	public Day(int day, int overflow)
	{
		this.day = day;
		this.overflow = overflow;
		this.time = 0;
	}
	
	/**
	 * constructor that takes 1 param indicating the daynumber 
	 * overflow is considerd to be 0
	 * @param day
	 */
	public Day(int day)
	{
		this(day, 0);
	}
	
	/**
	 * This function initializes the next day based on the current day
	 * it automatically passes the time that was worked to much today
	 * @return the next Day
	 */
	public Day getNextDay()
	{
		int nextOverflow = 0;
		if((this.time+this.overflow)>this.DAYTIME)
		{
			nextOverflow = this.time+this.overflow-this.DAYTIME;
		}
		return new Day(this.day + 1, nextOverflow);
	}
	
	/**
	 * inspector for the day variable
	 * @return this.day
	 */
	public int getDay()
	{
		return day;
	}
	
	/**
	 * inspector for the time variable
	 * @return this.time
	 */
	public int getTime()
	{
		return time;
	}
	
	/**
	 * inspector for the overflow
	 * @return this.overflow
	 */
	public int getOverFlow()
	{
		return overflow;
	}
	
	/**
	 * inspector that calculates that the day should be finished if we add
	 * additional time to this day
	 * @param additionalTime
	 * @return true if additionalTime + time + overflow >= DAYTIME
	 */
	public boolean shouldBeFinished(int additionalTime)
	{
		return ((this.overflow + this.time + additionalTime)>=this.DAYTIME);
	}
	
	/**
	 * inspector that calculates if the day should be finished base on the
	 * current time
	 * @return true if time + overflow >= DAYTIME
	 */
	public boolean shouldBeFinished()
	{
		return this.shouldBeFinished(0);
	}
	
	/**
	 * mutator that increases the time with the passed amount
	 * @param timeToIncrease
	 */
	public void increaseTime(int timeToIncrease)
	{
		this.time += timeToIncrease;
	}
	
	/**
	 * returns the time that a normal working day has
	 * @return
	 */
	public int StandardDayTime()
	{
		return this.DAYTIME;
	}
}
