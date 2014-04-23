package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import car.parts.Carpart;
import car.parts.CarpartsSet;
import util.LineReader;
import company.WorkStation;

public class AssemblyController implements UseCaseController
{
	private WorkStation workstation;
	
	public AssemblyController(WorkStation w)
	{
		workstation = w;
	}
	
	public List<Carpart> getTasksForWorkstation()
	{
		List<Carpart> l = new ArrayList<Carpart>();
		Iterator<Carpart> it = workstation.getPendingTasks().iterator();
		while(it.hasNext())
			l.add(it.next());
		return l;
	}
}
