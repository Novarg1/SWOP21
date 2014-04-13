package company;

import java.util.LinkedList;

import car.CarOrder;

public class CMCSystem 
{
	private NSchedule schedule;
	private AssemblyLine assemblyLine;
	
	private LinkedList<CarOrder> finishedOrders;
	
	public CMCSystem()
	{
		schedule = new NSchedule();
		
		WorkStation[] workstations = new WorkStation[3];
		workstations[0] = new CarBodyPost();
		workstations[1] = new DriveTrainPost();
		workstations[2] = new AccessoiresPost();
		assemblyLine = new AssemblyLine(workstations);
		
		finishedOrders = new LinkedList<CarOrder>();
	}
	
	public void placeOrder(CarOrder order)
	{
		this.schedule.addOrder(order);
	}
	
	public boolean canAdvance()
	{
		return this.assemblyLine.isReadyToAdvance();
	}
	
	public void advanceAssemblyLine(int time) throws Exception
	{
		if(!this.canAdvance())
		{
			throw new Exception();
		}
		
		if(time <= 0)
		{
			throw new IllegalArgumentException("cannot advance to same or previous time");
		}
		
		// there are 2 basic scenarios:
		// scenario 1: the next order can be pushed on the assembly line today
		// scenario 2: the next order is for tomorrow however the time has to be
		//             increased and the assembly line pushed
		CarOrder orderToPush = null;
		if(this.schedule.canNextOrderBeBuildToday(3))
		{
			orderToPush = this.schedule.prepareNextOrder();
		}
		CarOrder newlyFinished = this.assemblyLine.advance(orderToPush);
		this.schedule.increaseDayTime(time);
	}
	
	public void startNewDay()
	{
		this.schedule.startNewDay();
	}
}
