package controllers;

import java.util.ArrayList;
import java.util.List;

import user.User;
import car.Order;
import car.parts.Carpart;
import car.parts.CarpartsSet;
import company.AssemblyLine;
import company.CMCSystem;
import company.Schedule;
import company.Schedule.Algorithm;
import company.SchedulingAlgorithm;
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

	public User getLoggedInUser()
	{
		return cmcSytem.getLoggedInUser();
	}
	
	public void logInUser(int n)
	{
		cmcSytem.logInUser(n);
	}
	
	public boolean canAdvance()
	{
		AssemblyLine a = cmcSytem.getAssemblyLine();
		return a.isReadyToAdvance();
	}
	
	public List<Order> getScheduledOrdersFor(User user)
	{
		return cmcSytem.getScheduledOrdersForUser(user);
	}
	
	public List<Order> getFinishedOrdersFor(User user)
	{
		return cmcSytem.getFinishedOrdersForUser(user);
	}
	
	public boolean isWorkPostFinished(int id)
	{
		for(WorkStation w : cmcSytem.getAssemblyLine().getWorkstations())
		{
			if(w.getId() == id)
				return w.isReady();
		}
		return false;
	}
	
	public Schedule getSchedule()
	{
		return cmcSytem.getSchedule();
	}
	
	public List<WorkStation> getWorkstations()
	{
		List<WorkStation> list = new ArrayList<WorkStation>();
		for(WorkStation w : this.cmcSytem.getAssemblyLine().getWorkstations())
		{
			list.add(w);
		}
		return list;
	}
	
	public WorkStation getWorkstation(int id)
	{
		for(WorkStation w : this.cmcSytem.getAssemblyLine().getWorkstations())
			if(w.getId() == id)
				return w;
		return null;
	}
}
