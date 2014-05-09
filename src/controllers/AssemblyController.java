package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vehicle.parts.Carpart;
import company.WorkStation;
/**
 * assembly controller allows manipulation of 1 workstation
 * 
 * @author jonathanlangens
 *
 */
public class AssemblyController
{
	private WorkStation workstation;
	
	/**
	 * constructor takes a workstation and  sets its member to the passed station
	 * @param w
	 */
	public AssemblyController(WorkStation w)
	{
		workstation = w;
	}
	
	/**
	 * @return all unfinished tasks for this workstation
	 */
	public List<Carpart> getTasksForWorkstation()
	{
		List<Carpart> l = new ArrayList<Carpart>();
		Iterator<Carpart> it = workstation.getPendingTasks().iterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	}
	
	/**
	 * installs the passed carpart with t as the install time
	 * @param p
	 * @param t
	 */
	public void installPart(Carpart p, int t)
	{
		workstation.install(p, t);
	}
}
