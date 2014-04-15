package controllers;

import java.util.List;

import car.CarOrder;
import car.CarPart;
import company.AssemblyLine;
import company.CMCSystem;
import company.Schedule;
import company.WorkStation;

/** System Controller
 * 
 */

public class SystemController 
{	
	private CMCSystem cmcSytem;
	
	public SystemController()
	{
		this.cmcSytem = new CMCSystem();
	}

	public String getLoggedInUser()
	{
		return cmcSytem.getLoggedInUser();
	}
	
	public void logInUser(int n)
	{
		cmcSytem.logInUser(n);
	}
	
	public void advanceAssemblyLine(int time)
	{
		try {
			cmcSytem.advance(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startNewDay()
	{
		Schedule s = cmcSytem.getSchedule();
		s.startNewDay();
	}
	
	public int placeOrder(CarOrder order)
	{
		Schedule s = cmcSytem.getSchedule();
		return s.placeOrder(order);
	}
	
	public boolean canAdvance()
	{
		AssemblyLine a = cmcSytem.getAssemblyLine();
		return a.isReadyToAdvance();
	}
	
	public List<CarOrder> getScheduledOrdersFor(String user)
	{
		return cmcSytem.getScheduledOrdersForUser(user);
	}
	
	public List<CarOrder> getFinishedOrdersFor(String user)
	{
		return cmcSytem.getFinishedOrdersForUser(user);
	}
	
	public List<CarPart<?>> getWorkPostOverview(String id)
	{
		for(WorkStation w : cmcSytem.getAssemblyLine().getWorkstations())
		{
			if(w.getId() == id)
				return w.getPendingTasks();
		}
		return null;
	}
	
	public boolean isWorkPostFinished(String id)
	{
		for(WorkStation w : cmcSytem.getAssemblyLine().getWorkstations())
		{
			if(w.getId() == id)
				return w.isReady();
		}
		return false;
	}
}
