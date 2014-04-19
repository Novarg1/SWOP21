package controllers;

import java.util.List;

import car.Order;
import car.parts.Carpart;
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
	
	public int placeOrder(Order order)
	{
		Schedule s = cmcSytem.getSchedule();
		return s.placeOrder(order);
	}
	
	public boolean canAdvance()
	{
		AssemblyLine a = cmcSytem.getAssemblyLine();
		return a.isReadyToAdvance();
	}
	
	public List<Order> getScheduledOrdersFor(String user)
	{
		return cmcSytem.getScheduledOrdersForUser(user);
	}
	
	public List<Order> getFinishedOrdersFor(String user)
	{
		return cmcSytem.getFinishedOrdersForUser(user);
	}
	
	public List<Carpart> getWorkPostOverview(String id)
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
