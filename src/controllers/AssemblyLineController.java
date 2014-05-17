package controllers;

import company.assemblylines.Assemblyline;
import company.assemblylines.Assemblyline.Status;

public class AssemblyLineController {
	private Assemblyline assemblyLine;
	
	public AssemblyLineController(Assemblyline a)
	{
		this.assemblyLine = a;
	}
	
	public Status getStatus()
	{
		return this.assemblyLine.getStatus();
	}

	public void setStatus(Status s)
	{
		this.assemblyLine.setStatus(s);
	}
}
